package com.example.processorservice.service;

import dto.OrderDto;

public interface KafkaProducerService {
    void produce(OrderDto orderDto);
}
