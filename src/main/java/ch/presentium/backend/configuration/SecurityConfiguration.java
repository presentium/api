package ch.presentium.backend.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@AutoConfiguration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    var configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("authorization", "content-type"));
    configuration.setExposedHeaders(List.of());
    var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpConfigurer) throws Exception {
    // spotless:off
    return httpConfigurer
      .cors(withDefaults())
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(
        authorize -> authorize
          .requestMatchers(HttpMethod.OPTIONS).permitAll()
          .requestMatchers("/v1/auth/@me").authenticated()
          .requestMatchers("/v1/auth/**", "/oauth2/authorization/**").permitAll()
          .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/api-docs", "/api-docs/**").permitAll()
          .anyRequest().authenticated())
      .sessionManagement(
        session -> session
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .oauth2ResourceServer(
        oauth2 -> oauth2
          .jwt(withDefaults()))
      .build();
    // spotless:on
  }
}
