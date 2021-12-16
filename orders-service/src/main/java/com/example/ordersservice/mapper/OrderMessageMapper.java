package com.example.ordersservice.mapper;

import com.example.ordersservice.model.Order;
import org.example.dto.order.message.OrderMessageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMessageMapper {
    OrderMessageDto modelToDto(Order order);
    Order dtoToModel(OrderMessageDto dto);

}
