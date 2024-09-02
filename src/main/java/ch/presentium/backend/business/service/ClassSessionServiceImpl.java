package ch.presentium.backend.business.service;

import ch.presentium.backend.api.exception.SessionDayOfWeekMismatch;
import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import ch.presentium.backend.business.repository.ClassSessionRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassSessionServiceImpl implements ClassSessionService {

    private final ClassSessionRepository classSessionRepository;

    @Override
    public ClassSession createSession(SchoolClass schoolClass) {
        if (LocalDate.now().getDayOfWeek() != schoolClass.getDayOfWeek()) {
            throw new SessionDayOfWeekMismatch();
        }

        var date = LocalDateTime.of(LocalDate.now(), schoolClass.getStart());
        var session = classSessionRepository
            .findByDateAndSchoolClassId(date, schoolClass.getId())
            .orElseGet(() -> new ClassSession().setDate(date));

        schoolClass.addSession(session);

        return classSessionRepository.save(session);
    }

    @Override
    public List<ClassSession> getSessions(Long schoolClassId) {
        return classSessionRepository.findBySchoolClassId(schoolClassId);
    }
}
