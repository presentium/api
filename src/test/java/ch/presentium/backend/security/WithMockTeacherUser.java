package ch.presentium.backend.security;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.core.annotation.AliasFor;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@WithMockAuthenticatedUser(
    username = "teacher",
    roles = {"teacher"})
public @interface WithMockTeacherUser {

  /** Alias for {@link WithMockAuthenticatedUser#username()}. */
  @AliasFor(annotation = WithMockAuthenticatedUser.class)
  String username() default "teacher";

  /** Alias for {@link WithMockAuthenticatedUser#roles()}. */
  @AliasFor(annotation = WithMockAuthenticatedUser.class)
  String[] roles() default {"teacher"};
}
