package com.example.processorservice.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CommonService {

    <T> Object stringToObject(String str, Class<T> typeClass) throws JsonProcessingException;
    String objectToString(Object typeClass) throws JsonProcessingException;
}
