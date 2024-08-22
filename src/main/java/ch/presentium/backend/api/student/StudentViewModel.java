package ch.presentium.backend.api.student;

import ch.presentium.backend.api.security.model.UserViewModel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder

public class StudentViewModel {
    @NotNull private final UUID getId;
    @NotNull private final String getFirstName;
    @NotNull private final String getLastName;
    @NotNull private final String getEmail;
    private final UserViewModel getApiUser;
}

