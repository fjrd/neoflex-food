package com.example.processorservice.service;

import org.example.dto.delivery.message.DeliveryMessageDto;
import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.payment.message.PaymentMessageDto;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;

public interface ProcessorService {

    void processOrder(OrderMessageDto dto);
    void updatePayment(PaymentMessageDto dto);
    void updateRestaurantOrder(RestaurantOrderMessageDto dto);
    void updateDelivery(DeliveryMessageDto dto);
}
