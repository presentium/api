package ch.presentium.backend.configuration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.AbstractCommonTest;
import ch.presentium.backend.annotation.security.IsAdminUser;
import ch.presentium.backend.annotation.security.IsStudentUser;
import ch.presentium.backend.annotation.security.IsTeacherUser;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.UserRepository;
import ch.presentium.backend.business.service.security.JwtUserRegistration;
import ch.presentium.backend.configuration.SecurityConfigurationIntegrationTest.FixtureController;
import ch.presentium.backend.security.WithMockAdminUser;
import ch.presentium.backend.security.WithMockStudentUser;
import ch.presentium.backend.security.WithMockTeacherUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Import({ FixtureController.class, JwtUserRegistration.class })
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SecurityConfigurationIntegrationTest extends AbstractCommonTest {

    @Autowired
    private MockMvc api;

    @SpyBean
    private UserRepository userRepository;

    @Nested
    @DisplayName("Given a user is unauthenticated")
    class UserAnonymous {

        @Test
        void whenOptions_thenAllow() throws Exception {
            api
                .perform(options("/v1/auth/@me").header("Origin", "http://localhost:3000").with(csrf().asHeader()))
                .andExpect(status().isOk());
        }

        @Test
        void whenGetSelf_thenUnauthorized() throws Exception {
            api.perform(get("/v1/auth/@me")).andExpect(status().isUnauthorized());
        }
    }

    @Nested
    @DisplayName("Given a user is authenticated as a student")
    @WithMockStudentUser
    class UserStudent {

        @Test
        void whenConnecting_registersUser() throws Exception {
            verify(userRepository, times(1)).save(any(User.class));

            api
                .perform(get("/v1/auth/@me"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.username").value("student"),
                    jsonPath("$.email").value("student@test.presentium.ch"),
                    jsonPath("$.displayName").value("John Doe")
                );
        }

        @Test
        void whenGetSelf_thenOk() throws Exception {
            api.perform(get("/v1/auth/@me")).andExpect(status().isOk());
        }

        @Test
        void whenStudentOnly_thenOk() throws Exception {
            api.perform(get("/student-only")).andExpect(status().isOk());
        }

        @Test
        void whenTeacherOnly_thenForbidden() throws Exception {
            api.perform(get("/teacher-only")).andExpect(status().isForbidden());
        }

        @Test
        void whenAdminOnly_thenForbidden() throws Exception {
            api.perform(get("/admin-only")).andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("Given a user is authenticated as a teacher")
    @WithMockTeacherUser
    class UserTeacher {

        @Test
        void whenConnecting_registersUser() throws Exception {
            verify(userRepository, times(1)).save(any(User.class));

            api
                .perform(get("/v1/auth/@me"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.username").value("teacher"),
                    jsonPath("$.email").value("teacher@test.presentium.ch"),
                    jsonPath("$.displayName").value("John Doe")
                );
        }

        @Test
        void whenGetSelf_thenOk() throws Exception {
            api.perform(get("/v1/auth/@me")).andExpect(status().isOk());
        }

        @Test
        void whenStudentOnly_thenForbidden() throws Exception {
            api.perform(get("/student-only")).andExpect(status().isOk());
        }

        @Test
        void whenTeacherOnly_thenOk() throws Exception {
            api.perform(get("/teacher-only")).andExpect(status().isOk());
        }

        @Test
        void whenAdminOnly_thenForbidden() throws Exception {
            api.perform(get("/admin-only")).andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("Given a user is authenticated as an admin")
    @WithMockAdminUser
    class UserAdmin {

        @Test
        void whenConnecting_registersUser() throws Exception {
            verify(userRepository, times(1)).save(any(User.class));

            api
                .perform(get("/v1/auth/@me"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.username").value("admin"),
                    jsonPath("$.email").value("admin@test.presentium.ch"),
                    jsonPath("$.displayName").value("John Doe")
                );
        }

        @Test
        void whenGetSelf_thenOk() throws Exception {
            api.perform(get("/v1/auth/@me")).andExpect(status().isOk());
        }

        @Test
        void whenStudentOnly_thenForbidden() throws Exception {
            api.perform(get("/student-only")).andExpect(status().isOk());
        }

        @Test
        void whenTeacherOnly_thenForbidden() throws Exception {
            api.perform(get("/teacher-only")).andExpect(status().isOk());
        }

        @Test
        void whenAdminOnly_thenOk() throws Exception {
            api.perform(get("/admin-only")).andExpect(status().isOk());
        }
    }

    @RestController
    static class FixtureController {

        @GetMapping("/student-only")
        @IsStudentUser
        public void studentOnly() {}

        @GetMapping("/teacher-only")
        @IsTeacherUser
        public void teacherOnly() {}

        @GetMapping("/admin-only")
        @IsAdminUser
        public void adminOnly() {}
    }
}
