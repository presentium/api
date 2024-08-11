package ch.presentium.backend.configuration.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("info.build")
public record BuildInfoProperties(String version, String gitHash, String branchName, boolean dirty) {}
