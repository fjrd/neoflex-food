package com.example.processorservice.mapper;


import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.payment.message.PaymentDto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;

public class OrderDtoMapper {
    public static PaymentDto toPaymentToOrder(OrderMessageDto orderDto, PaymentDto paymentDto) {
        PaymentDto.builder()
                .cardNumber()
                .cvvCode()
                .cardExpireDate()
                .cardHolderName()
                .build();

        return orderDto;
    }
}
