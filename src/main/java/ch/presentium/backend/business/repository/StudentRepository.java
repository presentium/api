package ch.presentium.backend.business.repository;

import ch.presentium.backend.business.model.user.Student;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByCardId(String cardId);

    Optional<Student> findByFullName(String fullName);
}
