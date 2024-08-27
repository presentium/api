package ch.presentium.backend.api.schedule.student;

import ch.presentium.backend.business.service.StudentService;
import ch.presentium.backend.security.WithMockTeacherUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Collections;
import static org.mockito.Mockito.when;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Nested
    @DisplayName("GET /v1/students")
    class GetStudents {

        @WithMockTeacherUser
        void authenticated_noStudents_throwsNotFound() {
            when(studentService.findAll()).thenReturn(Collections.emptyList());
        }
    }
}
