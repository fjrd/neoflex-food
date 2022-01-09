package com.example.restaurantservice.service;

import org.example.dto.restaurant.request.RestaurantOrderRequestDto;
import org.example.dto.restaurant.response.RestaurantOrderResponseDto;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.example.dto.restaurant.request.DishRequestDto;

import java.util.List;

public interface RestaurantOrderService {

    List<RestaurantOrderResponseDto> getNewOrders();
    RestaurantOrderResponseDto updateOrder(RestaurantOrderRequestDto requestDto);
    void addNewOrder(RestaurantOrderMessageDto dto);
    String checkOrder(List<DishRequestDto> dishesList);
}
