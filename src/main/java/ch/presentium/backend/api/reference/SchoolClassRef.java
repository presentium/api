package ch.presentium.backend.api.reference;

import java.time.LocalTime;

public record SchoolClassRef(Long id, String name, CourseRef course, LocalTime startTime, LocalTime endTime) {}
