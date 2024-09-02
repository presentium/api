package ch.presentium.backend.business.service;

import ch.presentium.backend.api.common.DateRange;
import ch.presentium.backend.api.exception.ObjectNotFoundException;
import ch.presentium.backend.api.schedule.presence.model.PresenceViewModel;
import ch.presentium.backend.business.model.Presence;
import ch.presentium.backend.business.repository.ClassSessionRepository;
import ch.presentium.backend.business.repository.PresenceRepository;
import ch.presentium.backend.business.repository.StudentRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresenceServiceImpl implements PresenceService {

    private final ClassSessionRepository classSessionRepository;
    private final PresenceRepository presenceRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void addPresence(String studentCardId, UUID classSessionId) {
        var student = studentRepository
            .findByCardId(studentCardId)
            .orElseThrow(() -> ObjectNotFoundException.forStudent(studentCardId));

        var classSession = classSessionRepository
            .findById(classSessionId)
            .orElseThrow(() -> ObjectNotFoundException.forClassSession(classSessionId));

        presenceRepository.save(new Presence().setClassSession(classSession).setStudent(student));
    }

    @Override
    public List<PresenceViewModel> calculateAttendance(Long classId, DateRange dateRange, UUID studentId) {
        return presenceRepository.calculateAttendance(classId, dateRange, studentId);
    }
}
