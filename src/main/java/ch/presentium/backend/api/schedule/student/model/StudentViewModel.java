package ch.presentium.backend.api.schedule.student.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentViewModel {

    @NotNull private final UUID id;

    @NotNull private final String firstName;

    @NotNull private final String lastName;

    @NotNull private final String email;
}
