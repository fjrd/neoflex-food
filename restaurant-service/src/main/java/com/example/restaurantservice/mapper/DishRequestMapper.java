package com.example.restaurantservice.mapper;

import com.example.restaurantservice.model.Dish;
import org.example.dto.restaurant.request.DishRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishRequestMapper {

    DishRequestDto modelToDto(Dish dish);
    Dish dtoToModel(DishRequestDto dto);

}
