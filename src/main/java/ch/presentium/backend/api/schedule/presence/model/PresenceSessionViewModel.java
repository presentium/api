package ch.presentium.backend.api.schedule.presence.model;

import ch.presentium.backend.api.schedule.classsession.model.ClassSessionViewModel;
import ch.presentium.backend.api.schedule.student.model.StudentViewModel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PresenceSessionViewModel extends PresenceViewModel {
    @NotNull private final ClassSessionViewModel classSession;

    PresenceSessionViewModel(@NotNull StudentViewModel student,
                             @NotNull ClassSessionViewModel classSession,
                                @NotNull LocalDate date
    ) {
        super(student, date);
        this.classSession = classSession;
    }
}
