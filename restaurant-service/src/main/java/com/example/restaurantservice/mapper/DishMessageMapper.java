package com.example.restaurantservice.mapper;

import com.example.restaurantservice.model.Dish;
import org.example.dto.restaurant.message.DishMessageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishMessageMapper {

    DishMessageDto modelToDto(Dish dish);
    Dish dtoToModel(DishMessageDto dto);

}
