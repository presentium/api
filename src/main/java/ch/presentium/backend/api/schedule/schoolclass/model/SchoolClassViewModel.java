package ch.presentium.backend.api.schedule.schoolclass.model;

import ch.presentium.backend.api.schedule.course.model.CourseViewModel;
import ch.presentium.backend.api.schedule.room.model.RoomViewModel;
import ch.presentium.backend.api.schedule.teacher.model.TeacherPresenceModelView;
import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SchoolClassViewModel {

    @NotNull private final Long id;

    @NotNull private final String name;

    @NotNull private final CourseViewModel course;

    @NotNull private final DayOfWeek dayOfWeek;

    @NotNull private final LocalTime startTime;

    @NotNull private final LocalTime endTime;

    @NotNull private final RoomViewModel room;

    @NotNull private final TeacherPresenceModelView teacher;
}
