package ch.presentium.backend.api.user;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.business.model.user.Teacher;
import ch.presentium.backend.business.repository.TeacherRepository;
import ch.presentium.backend.security.WithMockStudentUser;
import ch.presentium.backend.security.WithMockTeacherUser;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest extends AbstractControllerTest {

    @MockBean
    private TeacherRepository teacherRepository;

    @Nested
    @DisplayName("GET /v1/teachers?refs")
    class GetTeacherRefs {

        @Test
        @WithMockStudentUser
        void asStudent_forbidden() throws Exception {
            api.perform(get("/v1/teachers?refs")).andExpect(status().isForbidden());
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_returnsStudentRefs() throws Exception {
            when(teacherRepository.findAll()).thenReturn(
                List.of(
                    (Teacher) new Teacher().setId(UUID.randomUUID()).setFirstName("Test").setLastName("One"),
                    (Teacher) new Teacher().setId(UUID.randomUUID()).setFirstName("Test").setLastName("Two"),
                    (Teacher) new Teacher().setId(UUID.randomUUID()).setFirstName("Test").setLastName("Three")
                )
            );

            api
                .perform(get("/v1/teachers?refs"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$[0].id").isString(),
                    jsonPath("$[0].name").value("Test One"),
                    jsonPath("$[1].id").isString(),
                    jsonPath("$[1].name").value("Test Three"),
                    jsonPath("$[2].id").isString(),
                    jsonPath("$[2].name").value("Test Two")
                );
        }
    }
}
