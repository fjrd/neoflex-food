package com.example.processorservice.service.impl;

import com.example.processorservice.service.KafkaConsumerService;
import com.example.processorservice.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.order.message.OrderMessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static final String KAFKA_FROM_ORDERS_TOPIC = "new_orders";
    private final ProcessorService service;

    @Override
    @KafkaListener(topics = KAFKA_FROM_ORDERS_TOPIC, groupId = "orders")
    public void loadOrders(OrderMessageDto dto) {
        log.info("loadOrders(), dto = {}", dto);
        service.processOrder(dto);
    }

}
