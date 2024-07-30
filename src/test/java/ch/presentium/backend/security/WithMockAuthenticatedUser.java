package ch.presentium.backend.security;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithSecurityContext;

/**
 * Annotation to mock an authenticated user in Spring Security for testing purposes.
 *
 * @see WithMockAdminUser
 * @see WithMockTeacherUser
 * @see WithMockStudentUser
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WithSecurityContext(factory = WithMockAuthenticatedUserSecurityContextFactory.class)
public @interface WithMockAuthenticatedUser {
    /**
     * The username to assign to the user.
     *
     * @return the username.
     */
    String username();

    /**
     * The roles that the user has. Presentium supports 'student', 'teacher', and 'admin'.
     *
     * @return the roles.
     */
    String[] roles();
}
