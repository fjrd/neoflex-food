package com.example.processorservice.service.impl;

import com.example.processorservice.mapper.OrderMapper;
import com.example.processorservice.service.KafkaProducerService;
import com.example.processorservice.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.payment.message.PaymentMessageDto;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {

    private final KafkaProducerService producerService;
    private final RedisTemplate<UUID, Object> redisTemplate;
    private final OrderMapper mapper;

    @Override
    public void processOrder(OrderMessageDto dto) {
        redisTemplate.opsForValue().set(dto.getOrderId(), dto);
        producerService.sendOrderToPaymentService(mapper.orderToPayment(dto));
    }

    @Override
    public void updatePayment(PaymentMessageDto dto) {
        OrderMessageDto order = (OrderMessageDto) redisTemplate.opsForValue().get(dto.getOrderId());
        order.setPaymentStatus(dto.getPaymentStatus());
        redisTemplate.opsForValue().set(order.getOrderId(), order);
        producerService.sendUpdatedOrderToOrdersService(order);
        switch (dto.getPaymentStatus()){
            case SUCCESS -> producerService.sendOrderToRestaurantService(mapper.orderToRestaurantOrder(order));
            case REJECTED, CANCELED -> redisTemplate.delete(order.getOrderId());
        }
    }

    @Override
    public void updateRestaurantOrder(RestaurantOrderMessageDto dto) {
        OrderMessageDto order = (OrderMessageDto) redisTemplate.opsForValue().get(dto.getOrderId());
        order.setRestaurantStatus(dto.getRestaurantStatus());
        redisTemplate.opsForValue().set(order.getOrderId(), order);
        producerService.sendUpdatedOrderToOrdersService(order);
        switch (dto.getRestaurantStatus()){
            case SUCCESS -> producerService.sendOrderToDeliveryService(mapper.orderToDelivery(order));
            case REJECTED, CANCELED -> producerService.rollbackPayments(mapper.orderToPayment(order));
        }
    }

    @Override
    public void updateDelivery(DeliveryMessageDto dto) {
        OrderMessageDto order = (OrderMessageDto) redisTemplate.opsForValue().getAndDelete(dto.getOrderId());
        order.setDeliveryStatus(dto.getDeliveryStatus());
        redisTemplate.opsForValue().set(order.getOrderId(), order);
        producerService.sendUpdatedOrderToOrdersService(order);
        switch (dto.getDeliveryStatus()){
            case SUCCESS -> {
                producerService.sendUpdatedOrderToOrdersService(order);
                redisTemplate.delete(order.getOrderId());
            }
            case REJECTED -> producerService.rollbackRestaurantOrders(mapper.orderToRestaurantOrder(order));
        }
    }
}
