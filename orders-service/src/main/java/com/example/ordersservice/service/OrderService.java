package com.example.ordersservice.service;

import dto.ClientOrderDto;
import dto.FullOrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<FullOrderDto> getOrdersByCustomerId(UUID customerId);
    FullOrderDto createOrder(UUID customerId, ClientOrderDto clientOrderDto);
    FullOrderDto updateOrder(UUID orderId, UUID customerId, ClientOrderDto clientOrderDto);
    void updateOrder(FullOrderDto fullOrderDto);
}
