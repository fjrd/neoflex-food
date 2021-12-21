package com.example.ordersservice.service.impl;

import com.example.ordersservice.mapper.OrderMessageMapper;
import com.example.ordersservice.mapper.OrderRequestMapper;
import com.example.ordersservice.mapper.OrderResponseMapper;
import com.example.ordersservice.model.Customer;
import com.example.ordersservice.model.Order;
import com.example.ordersservice.repository.CustomerRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@CacheConfig(cacheNames = "orders")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMessageMapper orderMessageMapper;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;
    private final KafkaProducerService kafkaProducerService;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    @Cacheable(key = "#customerId")
    public List<OrderResponseDto> getOrdersByCustomerId(UUID customerId) {
        log.info("getOrdersByCustomersId(), customerId = {}", customerId);

        return orderRepository.getAllByCustomer(customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("There is no such customer with ID = " + customerId)))
                .stream()
                .map(orderResponseMapper::modelToDto)
                .toList();
    }

    @Override
    @Transactional
    @CacheEvict(key = "#customerId")
    public OrderResponseDto createOrder(UUID customerId, OrderRequestDto requestDto) {
        log.info("createOrder(), orderDto = {}", requestDto);

        checkIfOrderExists(requestDto.getOrderId());

        Order order = orderRepository.saveAndFlush(orderRequestMapper.dtoToModel(requestDto)
                .toBuilder()
                .customer(customerRepository.findById(customerId)
                        .orElse(new Customer(customerId)))
                .build());

        kafkaProducerService.send(orderMessageMapper.modelToDto(order)
                .toBuilder()
                .cardDetails(requestDto.getCardDetails())
                .build());

        return orderResponseMapper.modelToDto(order);
    }


    @Override
    @Transactional
    @CacheEvict(key = "#customerId")
    public OrderResponseDto updateOrder(UUID customerId, OrderRequestDto requestDto) {
        log.info("createOrder(), customerId = {}, orderDto = {}", customerId, requestDto);

        Order order = findOrderByIdIfExists(requestDto.getOrderId());

        checkIfOrderBelongsToCustomer(order, customerId);

        order = orderRepository.saveAndFlush(order
                .toBuilder()
                .deliveryAddress(requestDto.getDeliveryAddress())
                .dishesList(requestDto.getDishesList())
                .build());

        kafkaProducerService.send(orderMessageMapper.modelToDto(order)
                .toBuilder()
                .cardDetails(requestDto.getCardDetails())
                .build());

        return orderResponseMapper.modelToDto(order);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#dto.customerId")
    public void updateOrderFromProcessor(OrderMessageDto dto) {
        log.info("updateOrder(), fullOrderDto = {}", dto);

        Order order = findOrderByIdIfExists(dto.getOrderId());

        orderRepository.saveAndFlush(order
                .toBuilder()
                .orderStatus(dto.getOrderStatus())
                .paymentStatus(dto.getPaymentStatus())
                .restaurantStatus(dto.getRestaurantStatus())
                .deliveryStatus(dto.getDeliveryStatus())
                .orderAmount(dto.getOrderAmount())
                .build());
    }


    private void checkIfOrderBelongsToCustomer(Order order, UUID customerId){
        if (!customerId.equals(order.getCustomer().getCustomerId()))
            throw new EntityNotFoundException("Customer with ID = " + customerId + " has no order with ID = " + order.getOrderId());
    }

    private void checkIfOrderExists(UUID orderId) {
        if (orderRepository.existsById(orderId))
            throw new EntityExistsException("Order with ID = " + orderId + " already exists");
    }

    private Order findOrderByIdIfExists(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("There is no such order with ID = " + orderId));
    }
}
