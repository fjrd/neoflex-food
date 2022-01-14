package com.example.ordersservice.mapper;

import com.example.ordersservice.model.DishOrder;
import org.example.dto.restaurant.DishMessageDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DishMessageMapper {

    @Mapping(source = "dish.dishId", target = "dishId")
    DishMessageDto modelToDto(DishOrder model);


}
