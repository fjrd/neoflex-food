package com.neoflex.gateway_api.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                return new BadRequestException("Bad request");
            case 401:
                return new NotValidException("Login or password not valid");
            case 404:
                return new NotFoundException("Application not found");
            case 500:
                return new ServerErrorException("Server error");
            default:
                return new Exception("Generic error");
        }
    }
}
