package com.example.processorservice.service;

import com.example.processorservice.service.common.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import dto.OrderDto;
import dto.PaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record KafkaProducerServiceImpl(KafkaTemplate<UUID, String> kafkaTemplate,
                                       CommonService commonService)
        implements KafkaProducerService {

    @Value("${kafka-manual-settings.topic-out}")
    private static String topicOrders;
    @Value("${kafka-manual-settings.topic-out-payments}")
    private static String topicPayments;

    @Override
    public void produceOrders(OrderDto orderDto) {

        try {
            kafkaTemplate.send(topicOrders, orderDto.getCustomerId(), commonService.objectToString(orderDto));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
 @Override
 public void producePayments(PaymentDto paymentDto) {

        try {
            kafkaTemplate.send(topicPayments, paymentDto.cardNumber(), commonService.objectToString(paymentDto));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
