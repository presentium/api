package ch.presentium.backend.api.schedule.presence.model;

import ch.presentium.backend.api.schedule.student.model.StudentViewModel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PresenceViewModel {
    @NotNull private final StudentViewModel student;
    @NotNull private final LocalDate date;
}
