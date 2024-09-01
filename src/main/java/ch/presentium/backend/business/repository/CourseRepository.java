package ch.presentium.backend.business.repository;

import ch.presentium.backend.business.model.schedule.Course;
import ch.presentium.backend.business.model.schedule.Semester;
import java.time.Year;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByNameAndSemesterAndYear(String name, Semester semester, Year year);
}
