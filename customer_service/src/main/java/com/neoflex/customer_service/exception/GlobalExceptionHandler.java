package com.neoflex.customer_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return exceptionHandler(e, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return exceptionHandler(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(NotValidException e) {
        return exceptionHandler(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return exceptionHandler(e, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ExceptionResponse> exceptionHandler(Exception e, HttpStatus status) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        log.warn(e.getMessage());
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
