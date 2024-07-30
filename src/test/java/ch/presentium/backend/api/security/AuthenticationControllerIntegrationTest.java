package ch.presentium.backend.api.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.api.AbstractControllerIntegrationTest;
import ch.presentium.backend.business.repository.UserRepository;
import ch.presentium.backend.common.TestUtils;
import ch.presentium.backend.security.WithMockAuthenticatedUser;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerIntegrationTest extends AbstractControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Nested
    @DisplayName("GET /v1/auth/@me")
    class GetSelf {

        @Test
        @WithMockAuthenticatedUser
        void authenticated_noUser_createsNew() throws Exception {
            api
                .perform(get("/v1/auth/@me"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.username").value("user"),
                    jsonPath("$.email").value("user@test.presentium.ch"),
                    jsonPath("$.firstName").value("John"),
                    jsonPath("$.lastName").value("Doe")
                );

            assertThat(userRepository.findById(UUID.fromString("ee11cbb1-9052-340b-87aa-c0ca060c23ee")))
                .isPresent()
                .get()
                .satisfies(user -> {
                    TestUtils.assertUser(user, "user", "user@test.presentium.ch", "John", "Doe");
                });
        }
    }
}
