package com.example.ordersservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
@ResponseBody
public class OrderControllerExceptionHandler {

    @ExceptionHandler(KafkaException.class)
    public ResponseEntity<Object> handleKafkaException(KafkaException exception){
        log.info("handleKafkaException()", exception);
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }
}
