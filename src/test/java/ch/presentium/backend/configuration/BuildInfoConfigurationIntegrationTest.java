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
            });
    }
}
