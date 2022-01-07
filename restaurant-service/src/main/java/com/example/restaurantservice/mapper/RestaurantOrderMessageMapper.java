package com.example.restaurantservice.mapper;

import com.example.restaurantservice.model.RestaurantOrder;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantOrderMessageMapper {

    RestaurantOrderMessageDto modelToDto(RestaurantOrder restaurantOrder);

    RestaurantOrder dtoToModel(RestaurantOrderMessageDto restaurantOrderMessageDto);

}
