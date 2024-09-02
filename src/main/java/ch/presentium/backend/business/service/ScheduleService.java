package ch.presentium.backend.business.service;

import ch.presentium.backend.business.model.schedule.Course;
import ch.presentium.backend.business.model.user.Teacher;
import java.util.List;
import net.fortuna.ical4j.model.Calendar;

/**
 * Service for managing courses and their schedules.
 */
public interface ScheduleService {
    /**
     * Imports a course from an iCal calendar.
     *
     * @param calendar The iCal calendar to import the course from.
     * @return The imported course.
     */
    List<Course> importCourses(Teacher teacher, Calendar calendar);
}
