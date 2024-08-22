package ch.presentium.backend.api.student;

import ch.presentium.backend.business.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("SELECT s.id as id, s.firstName as firstName, s.lastName as lastName, s.email as email, s.apiUser as apiUser FROM Student s")
    List<StudentViewModel> findAllView();
}
