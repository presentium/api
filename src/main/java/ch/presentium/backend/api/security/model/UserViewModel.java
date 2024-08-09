package ch.presentium.backend.api.security.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserViewModel {

    private final String username;
    private final String email;
    private final String displayName;
}
