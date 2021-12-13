package com.example.processorservice.service;

public interface KafkaProducerProcessOrderService {
    void produce(String orderDto);
}
