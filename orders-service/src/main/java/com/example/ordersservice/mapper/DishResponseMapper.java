package com.example.ordersservice.mapper;

import com.example.ordersservice.controller.dto.dish.DishResponseDto;
import com.example.ordersservice.model.DishOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DishResponseMapper {

    @Mapping(source = "dish.dishId", target = "dishId")
    DishResponseDto dtoToModel(DishOrder dishOrder);

}
