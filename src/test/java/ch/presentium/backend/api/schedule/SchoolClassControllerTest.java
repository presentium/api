package ch.presentium.backend.api.schedule;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.business.model.schedule.Course;
import ch.presentium.backend.business.model.schedule.Room;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import ch.presentium.backend.business.model.user.Teacher;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.UserRepository;
import ch.presentium.backend.security.WithMockStudentUser;
import ch.presentium.backend.security.WithMockTeacherUser;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(SchoolClassController.class)
class SchoolClassControllerTest extends AbstractControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Nested
    @DisplayName("GET /v1/school-classes?teacher=@me")
    class GetSelfClasses {

        @Test
        @WithMockStudentUser
        void asStudent_forbidden() throws Exception {
            api.perform(get("/v1/school-classes?teacher=@me")).andExpect(status().isForbidden());
            api.perform(get("/v1/school-classes?teacher=@me&today=true")).andExpect(status().isForbidden());
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_mapsClasses_doesNotFilterToday_sortsByName() throws Exception {
            when(userRepository.findById(anyString())).thenReturn(
                Optional.of(
                    new User()
                        .setPerson(
                            new Teacher()
                                .setClasses(
                                    Set.of(
                                        new SchoolClass()
                                            .setCourse(new Course().setName("PDG"))
                                            .setGroup("A")
                                            .setName("T1")
                                            .setRoom(new Room().setName("101")),
                                        new SchoolClass()
                                            .setCourse(new Course().setName("PDG"))
                                            .setGroup("A")
                                            .setName("T3")
                                            .setRoom(new Room().setName("101")),
                                        new SchoolClass()
                                            .setCourse(new Course().setName("PDG"))
                                            .setGroup("A")
                                            .setName("T2")
                                            .setRoom(new Room().setName("101"))
                                    )
                                )
                        )
                )
            );

            api
                .perform(get("/v1/school-classes?teacher=@me"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$[0].name").value("PDG-A-T1"),
                    jsonPath("$[0].room").value("101"),
                    jsonPath("$[1].name").value("PDG-A-T2"),
                    jsonPath("$[1].room").value("101"),
                    jsonPath("$[2].name").value("PDG-A-T3"),
                    jsonPath("$[2].room").value("101")
                );
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_mapsClasses_filterToday() throws Exception {
            var today = DayOfWeek.from(LocalDate.now());
            when(userRepository.findById(anyString())).thenReturn(
                Optional.of(
                    new User()
                        .setPerson(
                            new Teacher()
                                .setClasses(
                                    Set.of(
                                        new SchoolClass()
                                            .setCourse(new Course().setName("PDG"))
                                            .setGroup("A")
                                            .setName("T1")
                                            .setRoom(new Room().setName("101"))
                                            .setDayOfWeek(today),
                                        new SchoolClass()
                                            .setCourse(new Course().setName("PDG"))
                                            .setGroup("A")
                                            .setName("T3")
                                            .setRoom(new Room().setName("101"))
                                            .setDayOfWeek(today.minus(1)),
                                        new SchoolClass()
                                            .setCourse(new Course().setName("PDG"))
                                            .setGroup("A")
                                            .setName("T2")
                                            .setRoom(new Room().setName("101"))
                                            .setDayOfWeek(today.plus(1))
                                    )
                                )
                        )
                )
            );

            api
                .perform(get("/v1/school-classes?teacher=@me&today=true"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$[0].name").value("PDG-A-T1"),
                    jsonPath("$[0].room").value("101"),
                    jsonPath("$[0].dayOfWeek").value(today.name()),
                    jsonPath("$[1]").doesNotExist()
                );
        }
    }
}
