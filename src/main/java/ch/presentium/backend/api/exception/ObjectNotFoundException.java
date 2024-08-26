package ch.presentium.backend.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    private ObjectNotFoundException(String message) {
        super(message);
    }

    public static ObjectNotFoundException forUser(String username) {
        return new ObjectNotFoundException("User with username " + username + " not found");
    }
}
