package ch.presentium.backend.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.Map;
import java.util.stream.Collectors;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    in = SecuritySchemeIn.HEADER,
    bearerFormat = "JWT"
)
public class OpenApiConfiguration {

    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
            .group("v1")
            .displayName("Presentium API v1")
            .pathsToMatch("/v1/**")
            .addOpenApiCustomizer(openApi ->
                openApi
                    .info(
                        new Info()
                            .title("Presentium API v1")
                            .version("1.0")
                            .description("The Presentium API documentation")
                    )
                    .servers(
                        openApi
                            .getServers()
                            .stream()
                            .map(server -> server.url("%s/v1".formatted(server.getUrl())))
                            .toList()
                    )
                    .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                    .paths(
                        openApi
                            .getPaths()
                            .entrySet()
                            .stream()
                            .collect(
                                Collectors.toMap(
                                    entry -> entry.getKey().replaceFirst("/v1", ""),
                                    Map.Entry::getValue,
                                    (oldValue, newValue) -> oldValue,
                                    Paths::new
                                )
                            )
                    )
            )
            .build();
    }
}
