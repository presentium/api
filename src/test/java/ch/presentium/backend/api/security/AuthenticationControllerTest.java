package ch.presentium.backend.api.security;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.UserRepository;
import ch.presentium.backend.common.TestUtils;
import ch.presentium.backend.security.WithMockAuthenticatedUser;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest extends AbstractControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Nested
    @DisplayName("GET /v1/auth/@me")
    class GetSelf {

        @Test
        void unauthenticated_throws() throws Exception {
            api.perform(get("/v1/auth/@me")).andExpect(status().isUnauthorized());
        }

        @Test
        @WithMockAuthenticatedUser
        void authenticated_noUser_createsNew() throws Exception {
            when(userRepository.findById(any())).thenReturn(Optional.empty());
            when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

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

            verify(userRepository).save(
                assertArg(user -> TestUtils.assertUser(user, "user", "user@test.presentium.ch", "John", "Doe"))
            );
        }

        @Test
        @WithMockAuthenticatedUser
        void authenticated_existingUser_updatesExisting() throws Exception {
            var dbUser = new User();
            when(userRepository.findById(any())).thenReturn(Optional.of(dbUser));
            when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

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

            verify(userRepository).save(assertArg(user -> assertSame(dbUser, user)));
        }
    }
}
