package com.example.restaurantservice.mapper;

import com.example.restaurantservice.model.RestaurantOrder;
import org.example.dto.restaurant.request.RestaurantOrderRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantOrderRequestMapper {

    RestaurantOrderRequestDto modelToDto(RestaurantOrder restaurantOrder);
    RestaurantOrder dtoToModel(RestaurantOrderRequestDto restaurantOrderDto);

}
