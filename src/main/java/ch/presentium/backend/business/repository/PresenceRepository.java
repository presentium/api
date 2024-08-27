package ch.presentium.backend.business.repository;

import ch.presentium.backend.api.schedule.presence.model.PresenceViewPercentModel;
import ch.presentium.backend.business.model.Presence;
import ch.presentium.backend.api.types.daterange.DateRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PresenceRepository extends JpaRepository<Presence, UUID> {

    // Existing method
    @Query("SELECT p FROM Presence p WHERE p.student.id = :studentId")
    Presence findAllByStudentId(@Param("studentId") UUID studentId);

    @Query("SELECT new ch.presentium.backend.api.schedule.presence.model.PresenceViewPercentModel(" +
        "new ch.presentium.backend.api.schedule.student.model.StudentViewModel(s.id, s.firstName, s.lastName, s.email), " +
        "(CAST(COUNT(p) AS double) / " +
        "(SELECT COUNT(cs) FROM ClassSession cs WHERE cs.schoolClass.id = :classId) * 100)) " +
        "FROM Presence p " +
        "JOIN p.student s " +
        "WHERE p.classSession.schoolClass.id = :classId " +
        "AND p.classSession.date BETWEEN :#{#dateRange.startDate} AND :#{#dateRange.endDate} " +
        "AND (:studentId IS NULL OR s.id = :studentId) " +
        "GROUP BY s.id, s.firstName, s.lastName, s.email")
    List<PresenceViewPercentModel> calculateAttendancePercentage(
        @Param("classId") Long classId,
        DateRange dateRange,
        UUID studentId
    );
}
