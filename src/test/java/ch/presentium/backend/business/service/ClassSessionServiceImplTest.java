package ch.presentium.backend.business.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.presentium.backend.api.exception.SessionDayOfWeekMismatch;
import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import ch.presentium.backend.business.repository.ClassSessionRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClassSessionServiceImplTest {

    private @Mock ClassSessionRepository classSessionRepository;

    @InjectMocks
    private ClassSessionServiceImpl classSessionService;

    @Test
    void createSession_dowMismatch_throws() {
        var schoolClass = new SchoolClass().setDayOfWeek(DayOfWeek.from(LocalDate.now()).plus(1));
        assertThrows(SessionDayOfWeekMismatch.class, () -> classSessionService.createSession(schoolClass));
    }

    @Test
    void createSession_dowMatch_missingExisting_creates() {
        var schoolClass = new SchoolClass().setDayOfWeek(DayOfWeek.from(LocalDate.now())).setStart(LocalTime.of(10, 0));
        classSessionService.createSession(schoolClass);

        verify(classSessionRepository).save(
            assertArg(session -> {
                assertEquals(LocalDate.now(), session.getDate().toLocalDate());
                assertSame(schoolClass, session.getSchoolClass());
            })
        );
    }

    @Test
    void createSession_dowMatch_existing_updates() {
        var schoolClass = new SchoolClass().setDayOfWeek(DayOfWeek.from(LocalDate.now())).setStart(LocalTime.of(10, 0));
        var session = new ClassSession();
        session.setDate(LocalDate.now().atTime(10, 0));
        session.setSchoolClass(schoolClass);

        when(classSessionRepository.findByDateAndSchoolClassId(session.getDate(), schoolClass.getId())).thenReturn(
            Optional.of(session)
        );

        classSessionService.createSession(schoolClass);

        verify(classSessionRepository).save(
            assertArg(updatedSession -> {
                assertSame(session, updatedSession);
            })
        );
    }
}
