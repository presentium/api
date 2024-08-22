package ch.presentium.backend.api.student;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class StudentRequest {
    @NotNull private final String firstName;
    @NotNull private final String lastName;
    @NotNull private final String email;
}
