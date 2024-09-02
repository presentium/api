package ch.presentium.backend.api.schedule.model;

import ch.presentium.backend.api.reference.StudentRef;
import ch.presentium.backend.api.reference.TeacherRef;
import ch.presentium.backend.api.schedule.room.model.RoomViewModel;
import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolClassViewModel {

    @NotNull private final Long id;

    @NotNull private final String name;

    @NotNull private final CourseViewModel course;

    @NotNull private final DayOfWeek dayOfWeek;

    @NotNull private final LocalTime startTime;

    @NotNull private final LocalTime endTime;

    @NotNull private final RoomViewModel room;

    @NotNull private final TeacherRef teacher;

    @NotNull private final List<StudentRef> students;
}
