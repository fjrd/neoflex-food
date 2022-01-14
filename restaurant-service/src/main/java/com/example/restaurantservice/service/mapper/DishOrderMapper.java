package com.example.restaurantservice.service.mapper;

import com.example.restaurantservice.controller.dto.response.DishOrderResponseDto;
import com.example.restaurantservice.model.DishOrder;
import org.example.dto.restaurant.DishMessageDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DishOrderMapper {

    @InheritInverseConfiguration
    DishMessageDto modelToMessageDto(DishOrder dishOrder);

    @Mapping(source = "dishId", target = "id.dishId")
    DishOrder messageDtoToModel(DishMessageDto dto);

    @Mapping(source = "dish.dishId", target = "dishId")
    DishOrderResponseDto modelToResponseDto(DishOrder dishOrder);
}
