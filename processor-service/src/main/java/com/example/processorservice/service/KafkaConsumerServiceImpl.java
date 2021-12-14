package com.example.processorservice.service;

import com.example.processorservice.service.common.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import dto.OrderDto;
import dto.PaymentDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public record KafkaConsumerServiceImpl(CommonService commonService,
                                       ProcessorService processorService) implements KafkaConsumerService {
    @KafkaListener(topics = "${kafka-manual-settings.topic-in}")
    public void consumeOrders(String message) {
        try {
            var orderDto = (OrderDto) commonService.stringToObject(message, OrderDto.class);
            processorService.processOrders(orderDto);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = "${kafka-manual-settings.topic-in-payments}")
    public void consumePayments(String message) {
        try {
            var paymentDto = (PaymentDto) commonService.stringToObject(message, PaymentDto.class);
            processorService.processPayments(paymentDto);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
