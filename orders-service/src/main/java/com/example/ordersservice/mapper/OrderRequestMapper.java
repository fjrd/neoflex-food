package com.example.ordersservice.mapper;

import com.example.ordersservice.controller.dto.order.OrderRequestDto;
import com.example.ordersservice.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = DishRequestMapper.class, builder = @Builder(disableBuilder = true))
public interface OrderRequestMapper {

    Order dtoToModel(OrderRequestDto dto);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    void updateFromDtoToModel(OrderRequestDto dto, @MappingTarget Order order);

    @AfterMapping
    default void setOrderToDishOrders(@MappingTarget Order order){
        order.getDishesList().forEach(dishOrder -> dishOrder.setOrder(order));
    }
}
