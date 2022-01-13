package com.example.processorservice.mapper;

import org.example.dto.delivery.message.DeliveryMessageDto;
import org.example.dto.order.OrderMessageDto;
import org.example.dto.payment.message.PaymentMessageDto;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    PaymentMessageDto orderToPayment(OrderMessageDto orderMessageDto);
    RestaurantOrderMessageDto orderToRestaurantOrder(OrderMessageDto orderMessageDto);
    DeliveryMessageDto orderToDelivery(OrderMessageDto dto);

}
