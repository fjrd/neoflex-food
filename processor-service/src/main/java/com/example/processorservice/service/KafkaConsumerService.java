package com.example.processorservice.service;

import org.example.dto.delivery.message.DeliveryMessageDto;
import org.example.dto.order.OrderMessageDto;
import org.example.dto.payment.message.PaymentMessageDto;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;

public interface KafkaConsumerService {
    void getNewOrders(OrderMessageDto dto);
    void updateFromPayments(PaymentMessageDto dto);
    void updateFromRestaurants(RestaurantOrderMessageDto dto);
    void updateFromDelivery(DeliveryMessageDto dto);
}
