package com.example.processorservice.service;

import dto.PaymentDto;

public interface PaymentProcessService {
    void paymentsProcess(PaymentDto paymentDto);
}
