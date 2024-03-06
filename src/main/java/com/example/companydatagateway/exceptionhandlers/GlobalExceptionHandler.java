package com.example.companydatagateway.exceptionhandlers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponseBody> handleEntityNotFoundException(EntityNotFoundException exception) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(status, exception);
        return new ResponseEntity<>(exceptionResponseBody, status);
    }
}
