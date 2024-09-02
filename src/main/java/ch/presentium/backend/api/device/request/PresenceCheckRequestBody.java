package ch.presentium.backend.api.device.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PresenceCheckRequestBody(@NotNull @Positive Long schoolClass) {}
