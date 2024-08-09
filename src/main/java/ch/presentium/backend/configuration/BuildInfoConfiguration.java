package ch.presentium.backend.configuration;

import ch.presentium.backend.configuration.model.BuildInfoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BuildInfoProperties.class)
public class BuildInfoConfiguration {}
