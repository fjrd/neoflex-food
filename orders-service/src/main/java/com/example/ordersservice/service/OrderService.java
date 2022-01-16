package com.example.ordersservice.service;


import com.example.ordersservice.controller.dto.order.OrderRequestDto;
import com.example.ordersservice.controller.dto.order.OrderResponseDto;
import org.example.dto.order.OrderMessageDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderResponseDto> getOrdersByCustomerId(UUID customerId);
    OrderResponseDto createOrder(UUID customerId, OrderRequestDto clientOrderDto);
    OrderResponseDto updateOrder(UUID customerId, OrderRequestDto clientOrderDto);
    void updateOrderFromProcessor(OrderMessageDto fullOrderDto);
}
