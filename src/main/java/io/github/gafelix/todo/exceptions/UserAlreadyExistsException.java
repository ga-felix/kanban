package io.github.gafelix.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This email is already " +
        "registered.")
public class UserAlreadyExistsException extends RuntimeException {
}