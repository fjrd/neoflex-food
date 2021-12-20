package com.example.processorservice.mapper;


import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.payment.message.PaymentDto;

import java.math.BigDecimal;

public class OrderDtoMapper {
    public static PaymentDto toPaymentToOrder(OrderMessageDto orderDto, BigDecimal orderAmount) {
        var cardDetails = orderDto.getCardDetails();
        return PaymentDto.builder()
                .cardNumber(cardDetails.cardNumber())
                .cvvCode(cardDetails.cvc())
                .cardExpireDate(cardDetails.validDate())
                .firstName(cardDetails.firstName())
                .lastName(cardDetails.lastName())
//                .status(cardDetails)
                .orderAmount(orderAmount)
                .build();
    }
}
