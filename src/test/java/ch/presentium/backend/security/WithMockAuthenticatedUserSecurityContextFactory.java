package ch.presentium.backend.security;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

/**
 * Factory for creating a {@link SecurityContext} that can be used to mock an authenticated user in
 * Spring Security for testing purposes.
 *
 * @see WithMockAuthenticatedUser
 * @see WithMockAdminUser
 * @see WithMockTeacherUser
 * @see WithMockStudentUser
 */
@RequiredArgsConstructor
public class WithMockAuthenticatedUserSecurityContextFactory
    implements WithSecurityContextFactory<WithMockAuthenticatedUser> {

  private final JwtAuthenticationConverter jwtAuthenticationConverter;

  @Override
  public SecurityContext createSecurityContext(WithMockAuthenticatedUser annotation) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();

    var scope = Set.of("openid", "profile", "roles");
    var jwt =
        Jwt.withTokenValue("token")
            .header("alg", "none")
            .claim("sub", annotation.username())
            .claim("scope", scope)
            .claim("roles", Set.of(annotation.roles()))
            .build();

    context.setAuthentication(jwtAuthenticationConverter.convert(jwt));
    return context;
  }
}
