package ch.presentium.backend.business.repository;

import ch.presentium.backend.business.model.schedule.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {}
