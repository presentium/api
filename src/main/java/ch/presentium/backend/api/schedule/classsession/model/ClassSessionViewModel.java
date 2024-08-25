package ch.presentium.backend.api.schedule.classsession.model;

import ch.presentium.backend.api.schedule.schoolclass.model.SchoolClassViewModel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClassSessionViewModel {
    @NotNull private final SchoolClassViewModel schoolClass;
    @NotNull private final LocalDateTime date;
}
