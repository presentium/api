package ch.presentium.backend.api.schedule.model;

import ch.presentium.backend.business.model.schedule.Semester;
import jakarta.validation.constraints.NotNull;
import java.time.Year;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseViewModel {

    @NotNull private final String name;

    @NotNull private final Semester semester;

    @NotNull private final Year year;
}
