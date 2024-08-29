package ch.presentium.backend.api.device.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record EnrollStudentRequestBody(@NotNull UUID student) {}
