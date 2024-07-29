package ch.presentium.backend.security;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
public class WithMockAuthenticatedUserSecurityContextFactory
    implements WithSecurityContextFactory<WithMockAuthenticatedUser> {

  @Override
  public SecurityContext createSecurityContext(WithMockAuthenticatedUser annotation) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();

    var scope = Set.of("openid", "profile", "roles");
    var auth =
        new JwtAuthenticationToken(
            Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("sub", annotation.username())
                .claim("scope", scope)
                .claim("roles", Set.of(annotation.roles()))
                .build(),
            AuthorityUtils.createAuthorityList(
                Stream.concat(
                        scope.stream().map(s -> "SCOPE_" + s),
                        Arrays.stream(annotation.roles()).map(r -> "ROLE_" + r))
                    .toArray(String[]::new)),
            annotation.username());

    context.setAuthentication(auth);
    return context;
  }
}
