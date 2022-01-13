package com.example.ordersservice.mapper;


import com.example.ordersservice.controller.dto.dish.DishRequestDto;
import com.example.ordersservice.model.DishOrder;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DishRequestMapper {

    @Mapping(source = "dishId", target = "dish.dishId")
    @Mapping(source = "orderId", target = "order.orderId")
    @Mapping(source = "dishId", target = "id.dishId")
    @Mapping(source = "orderId", target = "id.orderId")
    DishOrder dishRequestDtoToModel(DishRequestDto dishRequestDto);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    void updateFromDtoToModel(DishRequestDto dto, @MappingTarget DishOrder dishOrder);

}
