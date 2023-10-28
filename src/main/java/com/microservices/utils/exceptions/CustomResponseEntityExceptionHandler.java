package com.microservices.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        System.err.println(ex);
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<Object> handleInvalidRequestException(MethodArgumentNotValidException ex, WebRequest request) {
        StringBuilder errorMessages = new StringBuilder();
        ex.getAllErrors().stream().forEach(objectError -> errorMessages.append(objectError.getDefaultMessage()).append(','));
        errorMessages.setLength(errorMessages.length()-1);
        ExceptionResponse response = new ExceptionResponse(new Date(), errorMessages.toString(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }


}
