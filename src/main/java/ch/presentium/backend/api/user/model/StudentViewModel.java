package ch.presentium.backend.api.user.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentViewModel {

    private final @NotNull UUID id;
    private final @NotNull String firstName;
    private final @NotNull String lastName;
    private final @NotNull String email;
}
