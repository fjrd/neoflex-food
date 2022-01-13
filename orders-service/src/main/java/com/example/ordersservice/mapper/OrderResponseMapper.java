package com.example.ordersservice.mapper;

import com.example.ordersservice.controller.dto.order.OrderResponseDto;
import com.example.ordersservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DishResponseMapper.class)
public interface OrderResponseMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(source = "assignedCourier.courierId", target = "assignedCourierId")
    OrderResponseDto modelToDto(Order order);

}
