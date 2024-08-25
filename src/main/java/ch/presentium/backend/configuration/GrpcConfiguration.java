package ch.presentium.backend.configuration;

import ch.presentium.backend.business.service.security.GrpcUserDetailsService;
import ch.presentium.backend.configuration.model.GrpcTlsProperties;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.ClientAuth;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import java.security.KeyFactory;
import java.util.List;
import net.devh.boot.grpc.server.security.authentication.CompositeGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.SSLContextGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.X509CertificateAuthenticationProvider;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.vault.config.EnvironmentVaultConfiguration;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultCertificateRequest;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "presentium.grpc", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import(EnvironmentVaultConfiguration.class)
@EnableConfigurationProperties(GrpcTlsProperties.class)
public class GrpcConfiguration {

    // mTLS configuration

    @Bean
    public GrpcServerConfigurer grpcServerConfigurer(GrpcTlsProperties tlsProperties, VaultTemplate vaultTemplate)
        throws Exception {
        var pkiOps = vaultTemplate.opsForPki(tlsProperties.pkiPath());

        var certRequest = VaultCertificateRequest.builder()
            .ttl(tlsProperties.certificateTtl())
            .commonName(tlsProperties.certificateCommonName())
            .build();

        var certResponse = pkiOps.issueCertificate(tlsProperties.certificateIssuerRoleName(), certRequest);
        var certBundle = certResponse.getRequiredData();

        var keyFactory = KeyFactory.getInstance(certBundle.getRequiredPrivateKeyType());
        var privateKey = keyFactory.generatePrivate(certBundle.getPrivateKeySpec());

        var deviceCertBundle = pkiOps.getIssuerCertificate(tlsProperties.deviceIssuerAuthority()).getRequiredData();

        return serverBuilder -> {
            if (!(serverBuilder instanceof NettyServerBuilder nsb)) {
                return;
            }

            try {
                nsb.sslContext(
                    GrpcSslContexts.configure(SslContextBuilder.forServer(privateKey, certBundle.getX509Certificate()))
                        .trustManager(deviceCertBundle.getX509Certificate())
                        .clientAuth(ClientAuth.REQUIRE)
                        .build()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    // device authentication

    @Bean
    public GrpcAuthenticationReader grpcAuthenticationReader() {
        return new CompositeGrpcAuthenticationReader(
            List.of(new SSLContextGrpcAuthenticationReader(), (call, headers) -> {
                throw new InsufficientAuthenticationException("Authentication did not complete");
            })
        );
    }

    @Bean
    public AuthenticationManager grpcAuthenticationProvider(GrpcUserDetailsService grpcUserDetailsService) {
        return new ProviderManager(List.of(new X509CertificateAuthenticationProvider(grpcUserDetailsService)));
    }
}
