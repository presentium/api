package ch.presentium.backend.api.schedule.model;

import ch.presentium.backend.api.reference.TeacherRef;
import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolClassViewModel {

    private final Long id;
    private final String name;

    private final DayOfWeek dayOfWeek;
    private final LocalTime start;
    private final LocalTime end;

    private final String room;
    private final TeacherRef teacher;
}
