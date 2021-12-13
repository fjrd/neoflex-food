package com.example.processorservice.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public record CommonServiceImpl() implements CommonService{

    @Override
    public OrderDto stringToOrderDto(String str) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        OrderDto orderDto;
        orderDto = mapper.readValue(str, new TypeReference<OrderDto>() {
        });
        return orderDto;
    }

    @Override
    public Object stringToObject(String str, Class type) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(str, type);
    }
}
