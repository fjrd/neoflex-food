package com.example.restaurantservice.service;

import com.example.restaurantservice.controller.dto.request.RestaurantOrderRequestDto;
import com.example.restaurantservice.controller.dto.response.RestaurantOrderResponseDto;
import org.example.dto.restaurant.RestaurantOrderMessageDto;
import com.example.restaurantservice.controller.dto.request.DishRequestDto;

import java.util.List;

public interface RestaurantOrderService {

    List<RestaurantOrderResponseDto> getNewOrders();
    RestaurantOrderResponseDto updateOrder(RestaurantOrderRequestDto requestDto);
    void addNewOrder(RestaurantOrderMessageDto dto);
    String checkOrder(List<DishRequestDto> dishesList);
}
