package ch.presentium.backend.business.service;

import ch.presentium.backend.business.model.Presence;
import ch.presentium.backend.business.repository.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PresenceService {
    private final PresenceRepository presenceRepository;

    @Autowired
    public PresenceService(PresenceRepository presenceRepository) {
        this.presenceRepository = presenceRepository;
    }

    public List<Presence> findAll() {
        return presenceRepository.findAll();
    }

    public Presence findAllByStudentId(UUID studentId) {
        return presenceRepository.findAllByStudentId(studentId);
    }
}
