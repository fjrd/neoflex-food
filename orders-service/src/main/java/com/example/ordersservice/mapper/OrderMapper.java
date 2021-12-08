package com.example.ordersservice.mapper;

import com.example.ordersservice.model.Order;
import dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto modelToDto(Order order);
    Order dtoToModel(OrderDto dto);

}
