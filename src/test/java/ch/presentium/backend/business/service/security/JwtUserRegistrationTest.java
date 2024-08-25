package ch.presentium.backend.business.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.presentium.backend.AbstractCommonTest;
import ch.presentium.backend.api.security.mapper.UserMapper;
import ch.presentium.backend.api.security.mapper.UserMapperImpl;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.jwt.Jwt;

@ExtendWith(MockitoExtension.class)
class JwtUserRegistrationTest extends AbstractCommonTest {

    private @Mock UserRepository userRepository;
    private @Spy UserMapper userMapper = new UserMapperImpl();

    private @InjectMocks JwtUserRegistration jwtUserRegistration;

    @Test
    void withJwt_noUser_createsUser() {
        var jwt = Jwt.withTokenValue("data")
            .subject("subject")
            .claim("username", "username")
            .claim("email", "email")
            .claim("name", "name")
            .header("header", "header")
            .build();

        jwtUserRegistration.registerUser(jwt);

        verify(userRepository).findById("subject");
        verify(userRepository).save(
            assertArg(user -> {
                assertEquals("subject", user.getId());
                assertEquals("username", user.getSubject());
                assertEquals("email", user.getEmail());
                assertEquals("name", user.getDisplayName());
            })
        );
    }

    @Test
    void withJwt_existingUser_updatesUser() {
        var user = new User();
        user.setId("subject");

        var jwt = Jwt.withTokenValue("data")
            .subject("subject")
            .claim("username", "username")
            .claim("email", "email")
            .claim("name", "name")
            .header("header", "header")
            .build();

        when(userRepository.findById("subject")).thenReturn(Optional.of(user));

        jwtUserRegistration.registerUser(jwt);

        verify(userRepository).findById("subject");
        verify(userRepository).save(assertArg(updatedUser -> assertSame(user, updatedUser)));
    }
}
