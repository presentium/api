package ch.presentium.backend.business.repository;

import ch.presentium.backend.api.common.DateRange;
import ch.presentium.backend.api.schedule.model.PresenceViewModel;
import ch.presentium.backend.business.model.Presence;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, UUID> {
    @Query(
        """
        SELECT new ch.presentium.backend.api.schedule.model.PresenceViewModel(
            new ch.presentium.backend.api.reference.CourseRef(sc.course.id, sc.course.name),
            new ch.presentium.backend.api.reference.SchoolClassRef(sc.id, sc.course.name || '-' || sc.group || '-' || sc.name),
            new ch.presentium.backend.api.reference.StudentRef(s.id, s.fullName),
            cs.date,
            CASE WHEN p.id IS NOT NULL THEN true ELSE false END
        )
        FROM SchoolClass sc
          JOIN sc.students s
          JOIN ClassSession cs ON cs.schoolClass.id = sc.id AND cs.date BETWEEN :#{#dateRange.start()} AND :#{#dateRange.end()}
          LEFT JOIN Presence p ON p.student.id = s.id AND p.classSession.id = cs.id
        WHERE (:classId IS NULL OR sc.id = :classId)
          AND (:studentId IS NULL OR s.id = :studentId)
        ORDER BY s.id, cs.date"""
    )
    List<PresenceViewModel> calculateAttendance(Long classId, DateRange dateRange, UUID studentId);
}
