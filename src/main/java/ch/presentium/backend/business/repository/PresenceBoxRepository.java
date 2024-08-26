package ch.presentium.backend.business.repository;

import ch.presentium.backend.business.model.PresenceBox;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceBoxRepository extends JpaRepository<PresenceBox, UUID> {
    Optional<PresenceBox> findByName(String name);
}
