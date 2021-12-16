package com.example.ordersservice.mapper;

import com.example.ordersservice.model.Order;
import org.example.dto.order.request.OrderRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderRequestMaper {
    OrderRequestDto modelToDto(Order order);
    Order dtoToModel(OrderRequestDto dto);
}
