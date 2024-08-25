package ch.presentium.backend.api.schedule.course.model;

import ch.presentium.backend.business.model.schedule.Semester;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Year;

@Data
@Builder
public class CourseViewModel {
    @NotNull private final String name;
    @NotNull private final Semester semester;
    @NotNull private final Year year;
}
