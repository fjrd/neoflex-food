package com.example.restaurantservice.service.mapper;

import com.example.restaurantservice.controller.dto.response.DishResponseDto;
import com.example.restaurantservice.model.Dish;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishMapper {

    DishResponseDto modelToResponseDto(Dish dish);

}
