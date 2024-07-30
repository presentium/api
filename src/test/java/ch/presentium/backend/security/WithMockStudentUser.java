package ch.presentium.backend.security;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.core.annotation.AliasFor;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WithMockAuthenticatedUser(username = "student", roles = { "student" })
public @interface WithMockStudentUser {
    /** Alias for {@link WithMockAuthenticatedUser#username()}. */
    @AliasFor(annotation = WithMockAuthenticatedUser.class)
    String username() default "student";

    /** Alias for {@link WithMockAuthenticatedUser#roles()}. */
    @AliasFor(annotation = WithMockAuthenticatedUser.class)
    String[] roles() default { "student" };
}
