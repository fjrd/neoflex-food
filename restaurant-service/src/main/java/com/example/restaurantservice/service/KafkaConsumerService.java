package com.example.restaurantservice.service;

import org.example.dto.restaurant.RestaurantOrderMessageDto;

public interface KafkaConsumerService {

    void addNewRestaurantOrder(RestaurantOrderMessageDto dto);

}
