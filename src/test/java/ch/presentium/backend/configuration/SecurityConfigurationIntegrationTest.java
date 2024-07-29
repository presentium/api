package ch.presentium.backend.configuration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.AbstractCommonTest;
import ch.presentium.backend.MockDatabaseConfiguration;
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
import org.springframework.context.annotation.Import;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Import({MockDatabaseConfiguration.class, FixtureController.class})
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SecurityConfigurationIntegrationTest extends AbstractCommonTest {

  @Autowired private MockMvc api;

  @Nested
  @DisplayName("Given a user is unauthenticated")
  class UserAnonymous {

    @Test
    void whenOptions_thenAllow() throws Exception {
      api.perform(
              options("/v1/auth/@me")
                  .header("Origin", "http://localhost:3000")
                  .with(csrf().asHeader()))
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
    void whenGetSelf_thenOk() throws Exception {
      api.perform(get("/v1/auth/@me")).andExpect(status().isOk());
    }

    @Test
    void whenStudentOnly_thenForbidden() throws Exception {
      api.perform(get("/student-only")).andExpect(status().isForbidden());
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
    void whenGetSelf_thenOk() throws Exception {
      api.perform(get("/v1/auth/@me")).andExpect(status().isOk());
    }

    @Test
    void whenStudentOnly_thenForbidden() throws Exception {
      api.perform(get("/student-only")).andExpect(status().isForbidden());
    }

    @Test
    void whenTeacherOnly_thenForbidden() throws Exception {
      api.perform(get("/teacher-only")).andExpect(status().isForbidden());
    }

    @Test
    void whenAdminOnly_thenOk() throws Exception {
      api.perform(get("/admin-only")).andExpect(status().isOk());
    }
  }

  @RestController
  static class FixtureController {

    @GetMapping("/student-only")
    @PreAuthorize("hasRole('student')")
    public void studentOnly() {}

    @GetMapping("/teacher-only")
    @PreAuthorize("hasRole('teacher')")
    public void teacherOnly() {}

    @GetMapping("/admin-only")
    @PreAuthorize("hasRole('admin')")
    public void adminOnly() {}
  }
}
