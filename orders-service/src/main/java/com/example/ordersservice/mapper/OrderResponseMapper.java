package com.example.ordersservice.mapper;

import com.example.ordersservice.model.Order;
import org.example.dto.order.response.OrderResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {
    OrderResponseDto modelToDto(Order order);
    Order dtoToModel(OrderResponseDto dto);

}
