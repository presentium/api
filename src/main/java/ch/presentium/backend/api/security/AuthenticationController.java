package ch.presentium.backend.api.security;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @GetMapping("/@me")
    public ResponseEntity<String> getAuthenticatedUser() {
        // TODO [lh] create real User metadata
        return ResponseEntity.ok(SecurityContextHolder.getContext().toString());
    }
}
