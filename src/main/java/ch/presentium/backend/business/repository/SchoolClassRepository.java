package ch.presentium.backend.business.repository;

import ch.presentium.backend.business.model.schedule.SchoolClass;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, UUID> {}
