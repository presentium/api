package ch.presentium.backend.annotation.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.access.prepost.PreAuthorize;

/** Annotate endpoints with this annotation to ensure the user has the admin role */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PreAuthorize("hasRole('admin')")
public @interface IsAdminUser {
}
