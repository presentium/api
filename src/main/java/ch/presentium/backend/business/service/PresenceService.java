package ch.presentium.backend.business.service;

import ch.presentium.backend.api.common.DateRange;
import ch.presentium.backend.api.schedule.presence.model.PresenceViewModel;
import java.util.List;
import java.util.UUID;

/**
 * Service for managing presence information.
 */
public interface PresenceService {
    /**
     * Add a presence for a student in a class session.
     *
     * @param studentCardId  student card ID
     * @param classSessionId class session ID
     */
    void addPresence(String studentCardId, UUID classSessionId);

    /**
     * Calculate attendance for a student in a class within a date range.
     * @param classId class ID to calculate attendance for. If null, calculate attendance for all classes.
     * @param dateRange date range to calculate attendance for. The range date is mandatory.
     * @param studentId student ID to calculate attendance for. If null, calculate attendance for all students.
     * @return list of attendance view models
     */
    List<PresenceViewModel> calculateAttendance(Long classId, DateRange dateRange, UUID studentId);
}
