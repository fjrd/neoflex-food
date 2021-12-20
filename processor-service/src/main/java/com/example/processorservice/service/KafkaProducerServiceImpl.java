package com.example.processorservice.service;

import com.example.processorservice.service.common.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.payment.message.PaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public record KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate,
                                       CommonService commonService)
        implements KafkaProducerService {

    @Value("${kafka-manual-settings.topic-out}")
    private static String topicOrders;
    @Value("${kafka-manual-settings.topic-out-payments}")
    private static String topicPayments;

    @Override
    public void produceOrders(OrderMessageDto orderMessageDto) {

        try {
            kafkaTemplate.send(topicOrders, orderMessageDto.getOrderId().toString(), commonService.objectToString(orderMessageDto));

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
