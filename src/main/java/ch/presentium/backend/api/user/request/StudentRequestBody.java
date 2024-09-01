package ch.presentium.backend.api.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequestBody(@NotBlank String fullName, @NotBlank @Email String email) {}
