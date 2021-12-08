package com.example.ordersservice.service;

import com.example.ordersservice.mapper.OrderMapper;
import com.example.ordersservice.repository.OrderRepository;
import dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper mapper;
    private final OrderRepository repository;


    @Override
    public List<OrderDto> getOrdersByCustomerId(UUID customerId) {
        log.info("getOrdersByCustomersId(), customerId = {}", customerId);
        //TODO
        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto dto) {
        log.info("createOrder(), dto = {}", dto);
        //TODO
        return null;
    }

    @Override
    public OrderDto updateOrder(OrderDto dto) {
        log.info("updateOrder(), dto = {}", dto);
        //TODO
        return null;
    }
}
