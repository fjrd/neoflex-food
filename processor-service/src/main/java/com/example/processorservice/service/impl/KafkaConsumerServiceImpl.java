package com.example.processorservice.service.impl;

import com.example.processorservice.service.KafkaConsumerService;
import com.example.processorservice.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.example.dto.order.OrderMessageDto;
import org.example.dto.payment.message.PaymentMessageDto;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static final String KAFKA_FROM_ORDERS_TOPIC = "new_orders";
    private static final String KAFKA_FROM_PAYMENT = "processed_payments";
    private static final String KAFKA_FROM_RESTAURANT = "processed_restaurant_orders";
    private static final String KAFKA_FROM_DELIVERY = "processed_deliveries";
    private final ProcessorService service;

    @Override
    @KafkaListener(topics = KAFKA_FROM_ORDERS_TOPIC, groupId = "orders")
    public void getNewOrders(String id, OrderMessageDto dto) {
        log.info("getNewOrders(), dto = {}", dto);
        service.processOrder(dto);
    }

    @Override
    @KafkaListener(topics = KAFKA_FROM_PAYMENT, groupId = "payment")
    public void updateFromPayments(PaymentMessageDto dto) {
        log.info("updateFromPayments(), dto = {}", dto);
        service.updatePayment(dto);
    }

    @Override
    @KafkaListener(topics = KAFKA_FROM_RESTAURANT, groupId = "Restaurant")
    public void updateFromRestaurants(RestaurantOrderMessageDto dto) {
        log.info("updateFromRestaurants(), dto = {}", dto);
        service.updateRestaurantOrder(dto);
    }

    @Override
    @KafkaListener(topics = KAFKA_FROM_DELIVERY, groupId = "delivery")
    public void updateFromDelivery(DeliveryMessageDto dto) {
        log.info("updateFromDelivery(), dto = {}", dto);
        service.updateDelivery(dto);
    }

}
