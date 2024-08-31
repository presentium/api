package ch.presentium.backend.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SessionDayOfWeekMismatch extends RuntimeException {

    public SessionDayOfWeekMismatch() {
        super("The session day of week does not match the owning school class");
    }
}
