package com.example.processorservice.service;

import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.payment.message.PaymentDto;

public interface KafkaProducerService {
    void produceOrders(OrderMessageDto orderMessageDto);

    void producePayments(PaymentDto paymentDto);
}
