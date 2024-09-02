package ch.presentium.backend.api.user.model;

import ch.presentium.backend.api.reference.SchoolClassRef;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentViewModel {

    private final @NotNull UUID id;
    private final @NotNull String fullName;
    private final String email;

    private final List<SchoolClassRef> schoolClasses;
}
