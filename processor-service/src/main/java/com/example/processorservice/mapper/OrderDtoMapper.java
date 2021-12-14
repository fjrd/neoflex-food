package com.example.processorservice.mapper;

import dto.OrderDto;
import dto.PaymentDto;

public class OrderDtoMapper {
    public static OrderDto paymentToOrder(OrderDto orderDto, PaymentDto paymentDto) {
        orderDto.setPaymentStatus(paymentDto.status().name());
        return orderDto;
    }
}
