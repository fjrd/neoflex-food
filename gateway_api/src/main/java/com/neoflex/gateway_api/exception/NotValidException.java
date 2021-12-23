package com.neoflex.gateway_api.exception;

public class NotValidException extends RuntimeException{
    public NotValidException(String message) {
        super(message);
    }
}
