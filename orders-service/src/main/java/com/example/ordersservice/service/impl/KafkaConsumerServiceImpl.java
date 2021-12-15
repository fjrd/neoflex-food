package com.example.ordersservice.service.impl;

import com.example.ordersservice.service.KafkaConsumerService;
import com.example.ordersservice.service.OrderService;
import dto.FullOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final OrderService service;

    @Override
    @KafkaListener(topics = "${KAFKA_TOPIC_FROM:processed_orders}", groupId = "orders")
    public void loadProcessedOrder(FullOrderDto dto) {
        KafkaConsumerServiceImpl.log.info("loadProcessedOrder(), orderDto = {}", dto);
        service.updateOrder(dto);
    }

}
