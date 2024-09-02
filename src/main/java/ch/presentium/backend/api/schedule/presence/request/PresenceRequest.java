package ch.presentium.backend.api.schedule.presence.request;

import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PresenceRequest {

    private final UUID studentId;

    @Positive private final Long classSessionId;
}
