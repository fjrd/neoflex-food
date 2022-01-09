package com.example.restaurantservice.service;

import org.example.dto.restaurant.message.RestaurantOrderMessageDto;

public interface KafkaProducerService {

    void send(RestaurantOrderMessageDto dto);

}
