package com.example.restaurantservice.mapper;

import com.example.restaurantservice.model.Dish;
import org.example.dto.restaurant.response.DishResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishResponseMapper {

    DishResponseDto modelToDto(Dish dish);
    Dish dtoToModel(DishResponseDto dto);

}
