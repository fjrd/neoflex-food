package com.example.payment_service.service;

import org.example.dto.payment.message.PaymentMessageDto;

public interface KafkaProducerService {

    void send(PaymentMessageDto dto);
}
