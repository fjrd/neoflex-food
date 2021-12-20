package com.example.processorservice.service;

import org.example.dto.order.message.OrderMessageDto;

public interface KafkaConsumerService {
    void loadOrders(OrderMessageDto dto);
}
