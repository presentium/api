package ch.presentium.backend.api.schedule;

import ch.presentium.backend.api.schedule.student.StudentController;
import ch.presentium.backend.business.service.StudentService;
import ch.presentium.backend.security.WithMockTeacherUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Nested
    @DisplayName("GET /v1/students/@me")
    class GetSelf {
        @Test
        @WithMockTeacherUser
        void authenticated_noUser_throwsNotFound() {
            when(studentService.findById(any())).thenReturn(Optional.empty());
        }
    }
}
