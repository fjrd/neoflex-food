package com.example.ordersservice.service.impl;

import com.example.ordersservice.mapper.OrderMessageMapper;
import com.example.ordersservice.mapper.OrderRequestMaper;
import com.example.ordersservice.mapper.OrderResponseMapper;
import com.example.ordersservice.model.Order;
import com.example.ordersservice.repository.OrderRepository;
import com.example.ordersservice.service.KafkaProducerService;
import com.example.ordersservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.order.request.OrderRequestDto;
import org.example.dto.order.response.OrderResponseDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.UUID;

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
    @Transactional
    @Cacheable(key = "#customerId")
    public List<OrderResponseDto> getOrdersByCustomerId(UUID customerId) {
        log.info("getOrdersByCustomersId(), customerId = {}", customerId);
        return repository.getAllByCustomerId(customerId)
                .stream()
                .map(orderResponseMapper::modelToDto)
                .toList();
    }

    @Override
    @Transactional
    @CacheEvict(key = "#customerId")
    public OrderResponseDto createOrder(UUID customerId, OrderRequestDto requestDto) {
        log.info("createOrder(), orderDto = {}", requestDto);

        if (repository.existsById(requestDto.getOrderId()))
            throw new EntityExistsException("This order already exists, order id = " + requestDto.getOrderId());

        Order order = orderRequestMaper.dtoToModel(requestDto);
        order.setCustomerId(customerId);
        order = repository.saveAndFlush(order);

        OrderMessageDto messageDto = orderMessageMapper.modelToDto(order);
        messageDto.setCardDetails(requestDto.getCardDetails());
        kafkaProducerService.send(messageDto);

        return orderResponseMapper.modelToDto(order);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#customerId")
    public OrderResponseDto updateOrder(UUID customerId, OrderRequestDto requestDto) {
        log.info("createOrder(), customerId = {}, orderDto = {}", customerId, requestDto);

        Order order = repository.findById(requestDto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no such order, order id = " + requestDto.getOrderId()));

        order.setDeliveryAddress(requestDto.getDeliveryAddress());
        order.setDishesList(requestDto.getDishesList());
        order = repository.saveAndFlush(order);

        OrderMessageDto messageDto = orderMessageMapper.modelToDto(order);
        messageDto.setCardDetails(requestDto.getCardDetails());
        kafkaProducerService.send(messageDto);

        return orderResponseMapper.modelToDto(order);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#dto.customerId")
    public Order updateOrder(OrderMessageDto dto) {
        log.info("updateOrder(), fullOrderDto = {}", dto);
        repository.findById(dto.getOrderId()).orElseThrow(() -> new ResourceNotFoundException(dto.getOrderId().toString()));
        return repository.saveAndFlush(orderMessageMapper.dtoToModel(dto));
    }
}
