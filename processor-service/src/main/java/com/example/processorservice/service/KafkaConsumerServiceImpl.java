package com.example.processorservice.service;

import com.example.processorservice.service.common.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.payment.message.PaymentDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public record KafkaConsumerServiceImpl(CommonService commonService,
                                       OrderProcessService orderProcessService,
                                       PaymentProcessService paymentProcessService) implements KafkaConsumerService {
    @KafkaListener(topics = "${kafka-manual-settings.topic-in}")
    public void consumeOrders(String message) {
        try {
            var orderDto = (OrderMessageDto) commonService.stringToObject(message, OrderMessageDto.class);
            orderProcessService.ordersProcess(orderDto);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "${kafka-manual-settings.topic-in-payments}")
    public void consumePayments(String message) {
        try {
            var paymentDto = (PaymentDto) commonService.stringToObject(message, PaymentDto.class);
            paymentProcessService.paymentsProcess(paymentDto);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
