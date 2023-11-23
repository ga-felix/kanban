package io.github.gafelix.todo.config;

import io.github.gafelix.todo.exceptions.EntityAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(buildResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNotFoundExceptions(Exception ex) {
        return new ResponseEntity<>(buildResponse(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, EntityAlreadyExistsException.class, MethodArgumentNotValidException.class, HttpMessageConversionException.class})
    public ResponseEntity<Object> handleBadRequestExceptions(Exception ex) {
        return new ResponseEntity<>(buildResponse(ex), HttpStatus.BAD_REQUEST);
    }

    private Map<Object, Object> buildResponse(Exception exception) {
        var body = new HashMap<>();
        body.put("message", exception.getLocalizedMessage());
        body.put("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        body.put("exception", exception.getClass().getSimpleName());
        return body;
    }

}