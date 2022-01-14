package com.example.payment_service.service.impl;

import com.example.payment_service.service.KafkaConsumerService;
import com.example.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.payment.message.PaymentMessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static final String KAFKA_FROM_TOPIC = "new_payments";
    private final PaymentService service;

    @Override
    @KafkaListener(topics = KAFKA_FROM_TOPIC, groupId = "payment")
    public void addNewPayment(PaymentMessageDto dto) {
        log.info("addNewPayment(), dto = {}", dto);
        service.addNewPayment(dto);
    }
}
