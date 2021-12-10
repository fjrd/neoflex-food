package com.example.ordersservice.service;

import com.example.ordersservice.mapper.OrderMapper;
import com.example.ordersservice.model.Order;
import com.example.ordersservice.repository.OrderRepository;
import dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@CacheConfig(cacheNames = "orders")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper mapper;
    private final OrderRepository repository;


    @Override
    @Cacheable
    public List<OrderDto> getOrdersByCustomerId(UUID customerId) {
        log.info("getOrdersByCustomersId(), customerId = {}", customerId);
        return repository.getOrdersByCustomerId(customerId)
                .stream()
                .map(mapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    @CachePut
    public OrderDto createOrder(OrderDto dto) {
        log.info("createOrder(), dto = {}", dto);
        Order savedOrder = repository.save(mapper.dtoToModel(dto));
        return mapper.modelToDto(savedOrder);
    }

    @Override
    @CachePut
    public OrderDto updateOrder(OrderDto dto) {
        log.info("updateOrder(), dto = {}", dto);
        Order savedOrder = repository.save(mapper.dtoToModel(dto));
        return mapper.modelToDto(savedOrder);
    }
}
