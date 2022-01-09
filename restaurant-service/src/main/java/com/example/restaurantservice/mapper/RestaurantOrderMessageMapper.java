package com.example.restaurantservice.mapper;

import com.example.restaurantservice.model.RestaurantOrder;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantOrderMessageMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(source = "order.orderId", target = "orderId")
    RestaurantOrderMessageDto modelToDto(RestaurantOrder restaurantOrder);

    @Mapping(source = "customerId", target = "customer.customerId")
    @Mapping(source = "orderId", target = "order.orderId")
    RestaurantOrder dtoToModel(RestaurantOrderMessageDto restaurantOrderMessageDto);

}
