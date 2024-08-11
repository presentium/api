package ch.presentium.backend.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import ch.presentium.backend.configuration.model.BuildInfoProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class BuildInfoConfigurationIntegrationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    void givenBuildInfoProperties_registersPropertyBean() {
        contextRunner
            .withUserConfiguration(BuildInfoConfiguration.class)
            .run(context -> {
                assertThat(context).hasSingleBean(BuildInfoProperties.class);
                assertThat(context.getBean(BuildInfoProperties.class))
                    .isNotNull()
                    .hasFieldOrPropertyWithValue("version", "v0.0.2-5-g1f7f12e")
                    .hasFieldOrPropertyWithValue("gitHash", "1f7f12ed0f")
                    .hasFieldOrPropertyWithValue("branchName", "main")
                    .hasFieldOrPropertyWithValue("dirty", false);
            });
    }
}
