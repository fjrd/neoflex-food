package com.example.processorservice.service;

import dto.OrderDto;
import dto.PaymentDto;

public interface ProcessorService {
    void processOrders(OrderDto orderDto);
    void processPayments(PaymentDto paymentDto);
}
