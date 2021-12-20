package com.example.ordersservice.mapper;

import com.example.ordersservice.model.Order;
import org.example.dto.order.response.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    OrderResponseDto modelToDto(Order order);

    @Mapping(source = "customerId", target = "customer.customerId")
    Order dtoToModel(OrderResponseDto dto);

}
