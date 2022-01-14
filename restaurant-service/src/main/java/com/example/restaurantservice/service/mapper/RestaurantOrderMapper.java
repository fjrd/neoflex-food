package com.example.restaurantservice.service.mapper;

import com.example.restaurantservice.controller.dto.request.RestaurantOrderRequestDto;
import com.example.restaurantservice.controller.dto.response.RestaurantOrderResponseDto;
import com.example.restaurantservice.model.RestaurantOrder;
import org.example.dto.restaurant.RestaurantOrderMessageDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = DishOrderMapper.class, builder = @Builder(disableBuilder = true))
public interface RestaurantOrderMapper {

    @InheritInverseConfiguration
    RestaurantOrderMessageDto modelToMessageDto(RestaurantOrder restaurantOrder);

    @Mapping(source = "customerId", target = "customer.customerId")
    @Mapping(source = "orderId", target = "restaurantOrderId")
    RestaurantOrder messageDtoToModel(RestaurantOrderMessageDto restaurantOrderMessageDto);

    @Mapping(source = "customer.customerId", target = "customerId")
    RestaurantOrderResponseDto modelToResponseDto(RestaurantOrder restaurantOrder);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    void updateFromDtoToModel(RestaurantOrderRequestDto dto, @MappingTarget RestaurantOrder restaurantOrder);

    @AfterMapping
    default void setRestaurantOrderToDishesList(@MappingTarget RestaurantOrder order){
        order.getDishesList().forEach(dishOrder -> {
            dishOrder.setRestaurantOrder(order);
            dishOrder.getId().setRestaurantOrderId(order.getRestaurantOrderId());
        });
    }
}
