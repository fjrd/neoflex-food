package com.example.ordersservice.service;

import org.example.dto.order.message.OrderMessageDto;

public interface KafkaConsumerService {

    void loadProcessedOrder(OrderMessageDto dto);

}
