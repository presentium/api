package ch.presentium.backend.configuration;

import ch.presentium.backend.configuration.model.BuildInfoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration(proxyBeanMethods = false)
@PropertySource("classpath:build.properties")
@EnableConfigurationProperties(BuildInfoProperties.class)
public class BuildInfoConfiguration {}
