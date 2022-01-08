package com.example.restaurantservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements com.example.restaurantservice.service.KafkaProducerService {

    private final KafkaTemplate<String, RestaurantOrderMessageDto> kafkaTemplate;
    private static final String KAFKA_TO_TOPIC = "processed_restaurant_orders";

    @Override
    public void send(RestaurantOrderMessageDto dto) {
        log.info("send(), dto = {}", dto);
        ListenableFuture<SendResult<String, RestaurantOrderMessageDto>> future = kafkaTemplate.send(KAFKA_TO_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent order = {}", dto);
                throw new KafkaException("failure when trying to send an order to Processor service");
            }

            @Override
            public void onSuccess(SendResult<String, RestaurantOrderMessageDto> result) {
                log.info("Successful message sending, orderDto = {}", dto);
            }
        });
    }
}
