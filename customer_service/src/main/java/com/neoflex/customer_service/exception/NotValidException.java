package com.neoflex.customer_service.exception;

public class NotValidException extends RuntimeException{
    public NotValidException(String message) {
        super(message);
    }
}
