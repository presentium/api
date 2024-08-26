package ch.presentium.backend.business.service;

import ch.presentium.backend.api.schedule.presence.model.PresenceViewPercentModel;
import ch.presentium.backend.business.model.Presence;
import ch.presentium.backend.business.repository.PresenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<PresenceViewPercentModel> calculateAttendancePercentage(Long classId) {
        return presenceRepository.calculateAttendancePercentage(classId);
    }
}
