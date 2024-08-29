package ch.presentium.backend.api.exception;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    private ObjectNotFoundException(String message) {
        super(message);
    }

    public static ObjectNotFoundException forUser(String username) {
        return new ObjectNotFoundException("User with username %s not found".formatted(username));
    }

    public static ObjectNotFoundException forStudent(UUID studentId) {
        return new ObjectNotFoundException("Student with id %s not found".formatted(studentId));
    }

    public static ObjectNotFoundException forStudent(String cardId) {
        return new ObjectNotFoundException("Student with card id %s not found".formatted(cardId));
    }

    public static ObjectNotFoundException forClassSession(UUID classSessionId) {
        return new ObjectNotFoundException("Class session with id %s not found".formatted(classSessionId));
    }
}
