package com.example.processorservice.service;

import org.example.dto.order.message.OrderMessageDto;

public interface KafkaProducerService {
    void sendUpdatedOrderToOrdersService(OrderMessageDto dto);
}
