package com.example.ordersservice.service.impl;

import com.example.ordersservice.mapper.OrderRequestMaper;
import com.example.ordersservice.mapper.OrderMessageMapper;
import com.example.ordersservice.mapper.OrderResponseMapper;
import com.example.ordersservice.model.Order;
import com.example.ordersservice.repository.OrderRepository;
import com.example.ordersservice.service.KafkaProducerService;
import com.example.ordersservice.service.OrderService;
import org.example.dto.order.request.OrderRequestDto;
import org.example.dto.order.message.OrderMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.order.response.OrderResponseDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@CacheConfig(cacheNames = "orders")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMessageMapper orderMessageMapper;
    private final OrderRequestMaper orderRequestMaper;
    private final OrderResponseMapper orderResponseMapper;
    private final KafkaProducerService kafkaProducerService;
    private final OrderRepository repository;

    @Override
    @Cacheable
    @Transactional
    public List<OrderResponseDto> getOrdersByCustomerId(UUID customerId) {
        log.info("getOrdersByCustomersId(), customerId = {}", customerId);
        return repository.getOrdersByCustomerId(customerId)
                .stream()
                .map(orderResponseMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponseDto createOrder(UUID customerId, OrderRequestDto clientOrderDto) {
        log.info("createOrder(), orderDto = {}", clientOrderDto);

        Order order = orderRequestMaper.dtoToModel(clientOrderDto);
        order.setCustomerId(customerId);
        order = repository.saveAndFlush(order);
        OrderMessageDto fullOrderDto = orderMessageMapper.modelToDto(order);
        fullOrderDto.setCardDetails(clientOrderDto.getCardDetails());
        kafkaProducerService.send(fullOrderDto);

        return orderResponseMapper.modelToDto(order);
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrder(UUID orderId, UUID customerId, OrderRequestDto clientOrderDto) {
        log.info("createOrder(), orderDto = {}", clientOrderDto);
        repository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(orderId.toString()));

        Order order = orderRequestMaper.dtoToModel(clientOrderDto);
        order.setOrderId(orderId);
        order.setCustomerId(customerId);
        order = repository.save(order);

        OrderMessageDto orderMessageDto = orderMessageMapper.modelToDto(order);
        kafkaProducerService.send(orderMessageDto);

        return orderResponseMapper.modelToDto(order);
    }

    @Override
    @Transactional
    public void updateOrder(OrderMessageDto dto) {
        log.info("updateOrder(), fullOrderDto = {}", dto);
        repository.findById(dto.getOrderId()).orElseThrow(() -> new ResourceNotFoundException(dto.getOrderId().toString()));
        repository.save(orderMessageMapper.dtoToModel(dto));
    }
}
