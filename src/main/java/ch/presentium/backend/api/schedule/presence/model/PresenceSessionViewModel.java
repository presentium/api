package ch.presentium.backend.api.schedule.presence.model;

import ch.presentium.backend.api.schedule.classsession.model.ClassSessionViewModel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class PresenceSessionViewModel extends PresenceViewModel {
    @NotNull private final ClassSessionViewModel classSession;
}
