package ch.presentium.backend;

import org.springframework.boot.SpringApplication;

/**
 * Test Application that launches the API bundled with Testcontainers for integration tests or
 * manual tests, it will provision an empty database and the necessary utilities for OIDC login.
 *
 * <p>Can be launched manually using './gradlew bootTestRun'.
 */
public class TestPresentiumApiApplication {

    public static void main(String[] args) {
        SpringApplication.from(PresentiumApiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
}
