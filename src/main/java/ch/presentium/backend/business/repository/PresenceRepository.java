package ch.presentium.backend.business.repository;

import ch.presentium.backend.api.common.DateRange;
import ch.presentium.backend.api.schedule.model.PresenceViewModel;
import ch.presentium.backend.business.model.Presence;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PresenceRepository extends JpaRepository<Presence, UUID> {
    // Existing method
    @Query("SELECT p FROM Presence p WHERE p.student.id = :studentId")
    Presence findAllByStudentId(@Param("studentId") UUID studentId);

    @Query(
        "SELECT new ch.presentium.backend.api.schedule.presence.model.PresenceViewModel(" +
        "new ch.presentium.backend.api.schedule.schoolclass.model.SchoolClassViewModel(sc.id, sc.name, " +
        "new ch.presentium.backend.api.schedule.course.model.CourseViewModel(sc.course.name, sc.course.semester, sc.course.year), " +
        "sc.dayOfWeek, sc.start, sc.end, " +
        "new ch.presentium.backend.api.schedule.room.model.RoomViewModel(sc.room.name), " +
        "new ch.presentium.backend.api.schedule.teacher.model.TeacherPresenceModelView(sc.teacher.firstName, sc.teacher.lastName)), " +
        "new ch.presentium.backend.api.schedule.student.model.StudentViewModel(s.id, s.firstName, s.lastName, s.email), " +
        "cs.date, " +
        "CASE WHEN p.id IS NOT NULL THEN true ELSE false END) " +
        "FROM SchoolClass sc " +
        "JOIN sc.students s " +
        "JOIN ClassSession cs ON cs.schoolClass.id = sc.id AND cs.date BETWEEN :#{#dateRange.start} AND :#{#dateRange.end} " +
        "LEFT JOIN Presence p ON p.student.id = s.id AND p.classSession.id = cs.id " +
        "WHERE (:classId IS NULL OR sc.id = :classId) " +
        "AND (:studentId IS NULL OR s.id = :studentId) " +
        "ORDER BY s.id, cs.date"
    )
    List<PresenceViewModel> calculateAttendance(
        @Param("classId") Long classId,
        @Param("dateRange") DateRange dateRange,
        @Param("studentId") UUID studentId
    );
}
