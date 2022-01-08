package com.example.restaurantservice.service;

import org.example.dto.restaurant.message.RestaurantOrderMessageDto;

public interface KafkaConsumerService {

    void addNewRestaurantOrder(RestaurantOrderMessageDto dto);

}
