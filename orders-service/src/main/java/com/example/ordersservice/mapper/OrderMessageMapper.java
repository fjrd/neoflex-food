package com.example.ordersservice.mapper;

import com.example.ordersservice.model.Order;
import org.example.dto.order.message.OrderMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMessageMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    OrderMessageDto modelToDto(Order order);

    @Mapping(source = "customerId", target = "customer.customerId")
    Order dtoToModel(OrderMessageDto dto);

}
