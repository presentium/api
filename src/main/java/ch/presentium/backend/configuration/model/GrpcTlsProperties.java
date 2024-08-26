package ch.presentium.backend.configuration.model;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

@ConfigurationProperties("presentium.grpc.tls")
public record GrpcTlsProperties(
    String pkiPath,
    String certificateIssuerRoleName,
    @DurationUnit(ChronoUnit.HOURS) Duration certificateTtl,
    String certificateCommonName,
    String deviceIssuerAuthority
) {}
