package ch.presentium.backend.api.schedule.teacher.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherPresenceModelView {

    @NotNull private final String firstName;

    @NotNull private final String lastName;
}
