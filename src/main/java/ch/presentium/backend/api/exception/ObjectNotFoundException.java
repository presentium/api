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

    public static ObjectNotFoundException forTeacher(UUID teacherId) {
        return new ObjectNotFoundException("Teacher with id %s not found".formatted(teacherId));
    }

    public static ObjectNotFoundException forSchoolClass(Long schoolClassId) {
        return new ObjectNotFoundException("School class with id %d not found".formatted(schoolClassId));
    }

    public static ObjectNotFoundException forClassSession(UUID classSessionId) {
        return new ObjectNotFoundException("Class session with id %s not found".formatted(classSessionId));
    }

    public static ObjectNotFoundException forDevice(UUID deviceId) {
        return new ObjectNotFoundException("Device with id %s not found".formatted(deviceId));
    }
}
