package ch.presentium.backend.api.schedule.model;

import ch.presentium.backend.api.reference.SchoolClassRef;
import ch.presentium.backend.api.reference.StudentRef;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PresenceViewModel(
    @NotNull SchoolClassRef schoolClass,
    @NotNull StudentRef student,
    @NotNull LocalDateTime date,
    @NotNull Boolean present
) {}
