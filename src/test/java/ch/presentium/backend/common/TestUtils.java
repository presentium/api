package ch.presentium.backend.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ch.presentium.backend.api.security.model.UserViewModel;
import ch.presentium.backend.business.model.user.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class TestUtils {

    public static void assertUser(UserViewModel viewModel, User user) {
        assertEquals(user.getSubject(), viewModel.getUsername());
        assertEquals(user.getEmail(), viewModel.getEmail());
        assertEquals(user.getDisplayName(), viewModel.getDisplayName());
    }

    public static void assertUser(UserViewModel expected, String username, String email, String displayName) {
        assertEquals(username, expected.getUsername());
        assertEquals(email, expected.getEmail());
        assertEquals(displayName, expected.getDisplayName());
    }

    public static void assertUser(User user, String username, String email, String displayName) {
        assertEquals(username, user.getSubject());
        assertEquals(email, user.getEmail());
        assertEquals(displayName, user.getDisplayName());
    }
}
