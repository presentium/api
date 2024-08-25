package ch.presentium.backend.api.schedule.presence.model;

import ch.presentium.backend.api.schedule.classsession.model.ClassSessionViewModel;
import ch.presentium.backend.api.schedule.student.model.StudentViewModel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PresenceViewModel {
    @NotNull private final StudentViewModel student;
    @NotNull private final ClassSessionViewModel classSession;
}
