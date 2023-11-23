package io.github.gafelix.todo.config;

import io.github.gafelix.todo.exceptions.EntityAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(buildResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException ex) {
        return new ResponseEntity<>(buildBindResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNotFoundExceptions(Exception ex) {
        return new ResponseEntity<>(buildResponse(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EntityAlreadyExistsException.class})
    public ResponseEntity<Object> handleBadRequestExceptions(Exception ex) {
        return new ResponseEntity<>(buildResponse(ex), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class, HttpMessageConversionException.class})
    public ResponseEntity<Object> handleBadRequestBindExceptions(BindException ex) {
        return new ResponseEntity<>(buildBindResponse(ex), HttpStatus.BAD_REQUEST);
    }

    private Map<Object, Object> buildResponse(Exception exception) {
        var body = new HashMap<>();
        body.put("message", exception.getLocalizedMessage());
        body.put("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        body.put("exception", exception.getClass().getSimpleName());
        return body;
    }
    private Map<Object, Object> buildBindResponse(BindException exception) {
        var body = new HashMap<>();
        List<String> errors = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        body.put("message", errors);
        body.put("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        body.put("exception", exception.getClass().getSimpleName());
        return body;
    }

}