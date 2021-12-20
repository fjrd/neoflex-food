package com.example.ordersservice.service.impl;

import com.example.ordersservice.service.KafkaConsumerService;
import com.example.ordersservice.service.OrderService;
import org.example.dto.order.message.OrderMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static final String KAFKA_FROM_TOPIC = "processed_orders";
    private final OrderService service;

    @Override
    @KafkaListener(topics = KAFKA_FROM_TOPIC, groupId = "orders")
    public void loadProcessedOrder(OrderMessageDto dto) {
        KafkaConsumerServiceImpl.log.info("loadProcessedOrder(), orderDto = {}", dto);
        service.updateOrder(dto);
    }

}
