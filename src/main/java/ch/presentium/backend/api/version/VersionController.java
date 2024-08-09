package ch.presentium.backend.api.version;

import ch.presentium.backend.api.version.model.VersionViewModel;
import ch.presentium.backend.configuration.model.BuildInfoProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Versioning", description = "Get version information about the API deployment")
@RequiredArgsConstructor
public class VersionController {

    private final BuildInfoProperties properties;

    @GetMapping("/version")
    @Operation(summary = "Get the version information")
    public VersionViewModel getVersionMetadata() {
        return VersionViewModel.builder()
            .version(properties.version())
            .gitHash(properties.gitHash())
            .branchName(properties.branchName())
            .build();
    }
}
