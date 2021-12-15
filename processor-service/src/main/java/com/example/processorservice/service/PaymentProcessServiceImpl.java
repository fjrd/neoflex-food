package com.example.processorservice.service;

import dto.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public record PaymentProcessServiceImpl() implements PaymentProcessService {
    @Override
    public void paymentsProcess(PaymentDto paymentDto) {

    }
}
