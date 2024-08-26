package ch.presentium.backend.api.schedule.presence.request;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Builder
@Jacksonized
public class PresenceRequest {
    private final UUID studentId;
    @Positive private final Long classSessionId;
}
