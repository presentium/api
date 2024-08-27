package ch.presentium.backend.business.repository;

import ch.presentium.backend.business.model.Presence;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, UUID> {}
