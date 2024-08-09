package ch.presentium.backend.api.version.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VersionViewModel {

    private final String version;
    private final String gitHash;
    private final String branchName;
}
