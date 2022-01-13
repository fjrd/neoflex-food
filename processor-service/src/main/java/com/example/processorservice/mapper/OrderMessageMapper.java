package com.example.processorservice.mapper;

import com.example.processorservice.model.OrderMessage;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.example.dto.order.OrderMessageDto;
import org.example.dto.payment.message.PaymentMessageDto;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMessageMapper {

    PaymentMessageDto orderToPayment(OrderMessageDto orderMessageDto);
    RestaurantOrderMessageDto orderToRestaurantOrder(OrderMessageDto orderMessageDto);
    DeliveryMessageDto orderToDelivery(OrderMessageDto dto);

    @Mapping(source = "orderId", target = "id")
    OrderMessage dtoToModel(OrderMessageDto dto);

    @InheritInverseConfiguration
    OrderMessageDto modelToDto(OrderMessage model);

}
