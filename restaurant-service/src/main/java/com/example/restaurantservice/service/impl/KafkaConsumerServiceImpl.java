package com.example.restaurantservice.service.impl;


import com.example.restaurantservice.service.KafkaConsumerService;
import com.example.restaurantservice.service.RestaurantOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.restaurant.RestaurantOrderMessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private static final String KAFKA_FROM_TOPIC = "new_restaurant_orders";
    private final RestaurantOrderService service;

    @Override
    @KafkaListener(topics = KAFKA_FROM_TOPIC, groupId = "restaurant")
    public void addNewRestaurantOrder(RestaurantOrderMessageDto dto) {
        log.info("loadNewRestaurantOrder(), dto = {}", dto);
        service.addNewOrder(dto);
    }
}
