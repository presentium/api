package ch.presentium.backend.api.schedule;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.repository.ClassSessionRepository;
import ch.presentium.backend.security.WithMockStudentUser;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(ClassSessionController.class)
class ClassSessionControllerTest extends AbstractControllerTest {

    @MockBean
    private ClassSessionRepository classSessionRepository;

    @Nested
    @DisplayName("GET /v1/school-classes/{schoolClassId}/sessions/dates")
    class GetSessionsDates {

        @Test
        @WithMockStudentUser
        void canGetDates() throws Exception {
            when(classSessionRepository.findBySchoolClassId(anyLong())).thenReturn(
                List.of(
                    new ClassSession().setDate(LocalDateTime.now().minusDays(1)),
                    new ClassSession().setDate(LocalDateTime.now().plusDays(1))
                )
            );

            api
                .perform(get("/v1/school-classes/1/sessions/dates"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$").isArray(),
                    jsonPath("$[0]").isString(),
                    jsonPath("$[1]").isString()
                );
        }
    }
}
