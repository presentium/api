package ch.presentium.backend.api.schedule.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.business.model.user.Student;
import ch.presentium.backend.business.service.StudentService;
import ch.presentium.backend.security.WithMockStudentUser;
import ch.presentium.backend.security.WithMockTeacherUser;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(StudentController.class)
public class StudentControllerTest extends AbstractControllerTest {

    @MockBean
    private StudentService studentService;

    @Nested
    @DisplayName("GET /v1/students")
    class GetStudent {

        @Test
        @WithMockStudentUser
        void get_all_students() throws Exception {
            when(studentService.findAll()).thenReturn(
                List.of(
                    (Student) new Student().setFirstName("Alice").setLastName("Test"),
                    (Student) new Student().setFirstName("Bob").setLastName("Test")
                )
            );

            api
                .perform(get("/v1/students"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.[0].firstName").value("Alice"),
                    jsonPath("$.[1].firstName").value("Bob")
                );
        }

        @Test
        @WithMockStudentUser
        void get_specific_student() throws Exception {
            when(studentService.findById(UUID.fromString("00000000-0000-0000-0000-000000000000"))).thenReturn(
                Optional.ofNullable(
                    (Student) new Student()
                        .setId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                        .setFirstName("Alice")
                        .setLastName("Test")
                )
            );

            api
                .perform(get("/v1/students/00000000-0000-0000-0000-000000000000"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.firstName").value("Alice")
                );
        }

        @Test
        @WithMockStudentUser
        void get_specific_student_not_found() throws Exception {
            when(studentService.findById(UUID.fromString("00000000-0000-0000-0000-000000000000"))).thenReturn(
                Optional.empty()
            );

            api.perform(get("/v1/students/00000000-0000-0000-0000-000000000000")).andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("POST /v1/students")
    class PostStudent {

        @Test
        @WithMockTeacherUser
        void create_student() throws Exception {
            api
                .perform(
                    post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            "{\"firstName\":\"Alice\",\"lastName\":\"Test\", \"email\":\"alice.test@test.presentium.ch\"}"
                        )
                )
                .andExpect(status().isCreated());

            verify(studentService, times(1)).addStudent(
                assertArg(student -> {
                    assertEquals("Alice", student.getFirstName());
                    assertEquals("Test", student.getLastName());
                })
            );
        }

        @Test
        @WithMockTeacherUser
        void create_student_no_email() throws Exception {
            api
                .perform(
                    post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Alice\",\"lastName\":\"Test\"}")
                )
                .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockTeacherUser
        void create_student_invalid_email() throws Exception {
            api
                .perform(
                    post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Alice\",\"lastName\":\"Test\", \"email\":\"alice.test\"}")
                )
                .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockTeacherUser
        void create_student_invalid_lastName() throws Exception {
            api
                .perform(
                    post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Alice\", \"email\":\"alice.test@test.presentium.ch\"}")
                )
                .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockTeacherUser
        void create_student_invalid_firstName() throws Exception {
            api
                .perform(
                    post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"lastName\":\"Test\", \"email\":\"alice.test@test.presentium.ch\"}")
                )
                .andExpect(status().isBadRequest());
        }
    }
}
