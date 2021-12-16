package com.example.ordersservice.service.impl;

import com.example.ordersservice.mapper.ClientOrderMapper;
import com.example.ordersservice.mapper.FullOrderMapper;
import com.example.ordersservice.model.Order;
import com.example.ordersservice.repository.OrderRepository;
import com.example.ordersservice.service.KafkaProducerService;
import com.example.ordersservice.service.OrderService;
import dto.ClientOrderDto;
import dto.FullOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@CacheConfig(cacheNames = "orders")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final FullOrderMapper fullOrderMapper;
    private final ClientOrderMapper clientOrderMapper;
    private final KafkaProducerService kafkaProducerService;
    private final OrderRepository repository;

    @Override
    @Cacheable
    public List<FullOrderDto> getOrdersByCustomerId(UUID customerId) {
        log.info("getOrdersByCustomersId(), customerId = {}", customerId);
        return repository.getOrdersByCustomerId(customerId)
                .stream()
                .map(fullOrderMapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FullOrderDto createOrder(UUID customerId, ClientOrderDto clientOrderDto) {
        log.info("createOrder(), orderDto = {}", clientOrderDto);

        Order order = clientOrderMapper.dtoToModel(clientOrderDto);
        order.setCustomerId(customerId);
        repository.save(order);

        FullOrderDto fullOrderDto = fullOrderMapper.modelToDto(order);
        fullOrderDto.setCardDetails(clientOrderDto.getCardDetails());
        kafkaProducerService.send(fullOrderDto);
        return fullOrderDto;
    }

    @Override
    public FullOrderDto updateOrder(UUID orderId, UUID customerId, ClientOrderDto clientOrderDto) {
        log.info("createOrder(), orderDto = {}", clientOrderDto);
        repository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(orderId.toString()));

        Order order = clientOrderMapper.dtoToModel(clientOrderDto);
        order = order.toBuilder().id(orderId).customerId(customerId).build();
        FullOrderDto fullOrderDto = fullOrderMapper.modelToDto(repository.save(order));
        kafkaProducerService.send(fullOrderDto);
        return fullOrderDto;
    }

    @Override
    public void updateOrder(FullOrderDto dto) {
        log.info("updateOrder(), fullOrderDto = {}", dto);
        repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException(dto.getId().toString()));
        repository.save(fullOrderMapper.dtoToModel(dto));
    }
}
