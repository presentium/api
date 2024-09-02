package ch.presentium.backend.api.schedule.model;

import ch.presentium.backend.api.reference.CourseRef;
import ch.presentium.backend.api.reference.SchoolClassRef;
import ch.presentium.backend.api.reference.StudentRef;
import java.time.LocalDateTime;

public record PresenceViewModel(
    CourseRef course,
    SchoolClassRef schoolClass,
    StudentRef student,
    LocalDateTime date,
    boolean present
) {}
