package com.example.ordersservice.service;

import org.example.dto.order.message.OrderMessageDto;

public interface KafkaProducerService {

    void send (OrderMessageDto dto);

}
