package com.example.processorservice.service;

import org.example.dto.payment.message.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public record PaymentProcessServiceImpl() implements PaymentProcessService {
    @Override
    public void paymentsProcess(PaymentDto paymentDto) {

    }
}
