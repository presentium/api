package ch.presentium.backend.configuration.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:build.properties")
@ConfigurationProperties("info.build")
public record BuildInfoProperties(String version, String gitHash, String branchName, boolean clean) {}
