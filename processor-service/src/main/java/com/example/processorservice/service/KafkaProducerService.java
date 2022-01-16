package com.example.processorservice.service;

import org.example.dto.delivery.message.DeliveryMessageDto;
import org.example.dto.order.OrderMessageDto;
import org.example.dto.payment.message.PaymentMessageDto;
import org.example.dto.restaurant.RestaurantOrderMessageDto;

public interface KafkaProducerService {
    void sendUpdatedOrderToOrdersService(OrderMessageDto dto);
    void sendOrderToPaymentService(PaymentMessageDto dto);
    void sendOrderToRestaurantService(RestaurantOrderMessageDto dto);
    void sendOrderToDeliveryService(DeliveryMessageDto dto);
    void rollbackPayments(PaymentMessageDto dto);
    void rollbackRestaurantOrders(RestaurantOrderMessageDto dto);
    void rollbackDeliveries(DeliveryMessageDto dto);

}
