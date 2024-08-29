package ch.presentium.backend.api.schedule.student.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class StudentRequest {

    private final @NotBlank String firstName;
    private final @NotBlank String lastName;
    private final @NotBlank @Email String email;
}
