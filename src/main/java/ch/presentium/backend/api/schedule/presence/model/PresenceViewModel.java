package ch.presentium.backend.api.schedule.presence.model;

import ch.presentium.backend.api.schedule.schoolclass.model.SchoolClassViewModel;
import ch.presentium.backend.api.schedule.student.model.StudentViewModel;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class PresenceViewModel {

    @NotNull private final SchoolClassViewModel schoolClass;

    @NotNull private final StudentViewModel student;

    @NotNull private final LocalDateTime date;

    @NotNull private final Boolean present;
}
