package ch.presentium.backend.business.repository;

import ch.presentium.backend.business.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PresenceRepository extends JpaRepository<Presence, UUID> {

    @Query("SELECT p FROM Presence p WHERE p.student.id = :studentId")
    Presence findAllByStudentId(UUID studentId);
}
