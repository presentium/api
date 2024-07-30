package ch.presentium.backend.api.security;

import ch.presentium.backend.api.security.mapper.UserMapper;
import ch.presentium.backend.api.security.model.UserViewModel;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication-related operations")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/@me")
    @Transactional(rollbackFor = Throwable.class)
    @Operation(summary = "Get the authenticated user metadata")
    public UserViewModel getAuthenticatedUser(@AuthenticationPrincipal Jwt jwt) {
        var user = userRepository.findById(UUID.fromString(jwt.getSubject())).orElseGet(User::new);
        userMapper.updateUser(jwt, user);
        return userMapper.toViewModel(userRepository.save(user));
    }
}
