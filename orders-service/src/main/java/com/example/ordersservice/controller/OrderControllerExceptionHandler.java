package com.example.ordersservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityExistsException;

@Slf4j
@ControllerAdvice
@ResponseBody
public class OrderControllerExceptionHandler {

    @ExceptionHandler(KafkaException.class)
    public ResponseEntity<String> handleKafkaException(KafkaException exception){
        log.info("handleKafkaException()", exception);
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handleEntityExistsException(EntityExistsException exception){
        log.info("handleEntityExistsException()", exception);
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
