package ch.presentium.backend.api.schedule.presence.model;

import ch.presentium.backend.api.schedule.student.model.StudentViewModel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@RequiredArgsConstructor
public class PresenceViewPercentModel {
    @NotNull private final StudentViewModel student;
    @NotNull private final double presencePercent;
    @NotNull private final LocalDateTime date;
}
