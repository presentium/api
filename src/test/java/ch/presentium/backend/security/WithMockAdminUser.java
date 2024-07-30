package ch.presentium.backend.security;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.core.annotation.AliasFor;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WithMockAuthenticatedUser(username = "admin", roles = { "admin" })
public @interface WithMockAdminUser {
    /** Alias for {@link WithMockAuthenticatedUser#username()}. */
    @AliasFor(annotation = WithMockAuthenticatedUser.class)
    String username() default "admin";

    /** Alias for {@link WithMockAuthenticatedUser#roles()}. */
    @AliasFor(annotation = WithMockAuthenticatedUser.class)
    String[] roles() default { "admin" };
}
