package ch.presentium.backend.api.schedule.student;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.business.model.user.Student;
import ch.presentium.backend.business.service.StudentService;
import ch.presentium.backend.security.WithMockStudentUser;
import ch.presentium.backend.security.WithMockTeacherUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest extends AbstractControllerTest {

    @MockBean
    private StudentService studentService;

    @Nested
    @DisplayName("GET /v1/students")
    class GetStudent {

        @Test
        @WithMockStudentUser
        void authenticated_noUser_throwsNotFound() throws Exception {
            when(studentService.findAll()).thenReturn(List.of(
                (Student) new Student().setFirstName("Alice").setLastName("Test"),
                (Student) new Student().setFirstName("Bob").setLastName("Test")
            ));

            api.perform(get("/v1/students")).andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.[0].firstName").value("Alice"),
                jsonPath("$.[1].firstName").value("Bob")
            );
        }
    }
}
