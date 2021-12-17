package com.example.processorservice.service;


import org.example.dto.payment.message.PaymentDto;

public interface PaymentProcessService {
    void paymentsProcess(PaymentDto paymentDto);
}
