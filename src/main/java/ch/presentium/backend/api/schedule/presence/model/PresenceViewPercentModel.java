package ch.presentium.backend.api.schedule.presence.model;

import ch.presentium.backend.api.schedule.student.model.StudentViewModel;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PresenceViewPercentModel extends PresenceViewModel {
    private double presencePercent;

    PresenceViewPercentModel(@NotNull StudentViewModel student, double presencePercent) {
        super(student);
        this.presencePercent = presencePercent;
    }
}
