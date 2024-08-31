package ch.presentium.backend.business.repository;

import ch.presentium.backend.api.schedule.presence.model.PresenceViewPercentModel;
import ch.presentium.backend.business.model.Presence;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
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
        "GROUP BY s.id, s.firstName, s.lastName, s.email")
    List<PresenceViewPercentModel> calculateAttendancePercentage(@Param("classId") Long classId);


}
