package com.example.payment_service.service;

import org.example.dto.payment.message.PaymentMessageDto;

public interface PaymentService {

    void addNewPayment(PaymentMessageDto dto);
}
