package ch.presentium.backend.api.schedule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.api.reference.StudentRef;
import ch.presentium.backend.api.schedule.model.PresenceViewModel;
import ch.presentium.backend.business.repository.PresenceRepository;
import ch.presentium.backend.security.WithMockStudentUser;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PresenceController.class)
class PresenceControllerTest extends AbstractControllerTest {

    @MockBean
    private PresenceRepository presenceRepository;

    @Nested
    @DisplayName("GET /v1/students/{studentId}/school-classes/{schoolClassId}/presences")
    class GetPresences {

        @Test
        @WithMockStudentUser
        void getPresences() throws Exception {
            when(presenceRepository.calculateAttendance(any(), anyLong(), any())).thenReturn(
                List.of(
                    new PresenceViewModel(new StudentRef(UUID.randomUUID(), "name"), LocalDateTime.now(), true),
                    new PresenceViewModel(new StudentRef(UUID.randomUUID(), "name"), LocalDateTime.now(), false)
                )
            );

            api
                .perform(
                    get("/v1/students/{studentId}/school-classes/{schoolClassId}/presences", UUID.randomUUID(), 1L)
                        .param("start", "2021-01-01T00:00:00")
                        .param("end", "2021-01-02T00:00:00")
                )
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    MockMvcResultMatchers.jsonPath("$").isArray(),
                    jsonPath("$[0].student.id").isString(),
                    jsonPath("$[0].student.name").value("name"),
                    jsonPath("$[0].date").isNotEmpty(),
                    jsonPath("$[0].present").value(true),
                    jsonPath("$[1].student.id").isString(),
                    jsonPath("$[1].student.name").value("name"),
                    jsonPath("$[1].date").isNotEmpty(),
                    jsonPath("$[1].present").value(false)
                );

            verify(presenceRepository).calculateAttendance(
                any(),
                anyLong(),
                assertArg(dateRange -> {
                    dateRange.start().isEqual(LocalDateTime.parse("2021-01-01T00:00:00"));
                    dateRange.end().isEqual(LocalDateTime.parse("2021-01-02T00:00:00"));
                })
            );
        }
    }
}
