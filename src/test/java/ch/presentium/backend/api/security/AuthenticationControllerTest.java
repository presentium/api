package ch.presentium.backend.api.security;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.business.repository.UserRepository;
import ch.presentium.backend.security.WithMockAuthenticatedUser;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        void authenticated_noUser_throwsNotFound() throws Exception {
            when(userRepository.findById(any())).thenReturn(Optional.empty());
            when(userRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

            api.perform(get("/v1/auth/@me")).andExpect(status().isNotFound());
        }
    }
}
