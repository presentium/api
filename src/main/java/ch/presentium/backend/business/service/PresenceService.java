package ch.presentium.backend.business.service;

import ch.presentium.backend.api.schedule.presence.model.PresenceViewModel;
import ch.presentium.backend.api.types.daterange.DateRange;
import ch.presentium.backend.business.model.Presence;
import ch.presentium.backend.business.repository.PresenceRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PresenceService {

    private final PresenceRepository presenceRepository;

    public List<Presence> findAll() {
        return presenceRepository.findAll();
    }

    public Presence findAllByStudentId(UUID studentId) {
        return presenceRepository.findAllByStudentId(studentId);
    }

    public List<PresenceViewModel> calculateAttendance(Long classId, DateRange dateRange, UUID studentId) {
        return presenceRepository.calculateAttendance(classId, dateRange, studentId);
    }
}
