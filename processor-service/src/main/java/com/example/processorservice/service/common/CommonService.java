package com.example.processorservice.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.OrderDto;

public interface CommonService {
    OrderDto stringToOrderDto(String str) throws JsonProcessingException;
    Object stringToObject(String str, Class type) throws JsonProcessingException;
}
