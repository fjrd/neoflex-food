package com.example.processorservice.service;

import dto.OrderDto;
import dto.PaymentDto;

public interface KafkaProducerService {
    void produceOrders(OrderDto orderDto);

    void producePayments(PaymentDto paymentDto);
}
