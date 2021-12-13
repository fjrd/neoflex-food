package com.example.processorservice.service;

import com.example.processorservice.service.common.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public record KafkaConsumerNewOrderServiceImpl(CommonService commonService) implements KafkaConsumerNewOrderService {
    @KafkaListener(topics = "${kafka-manual-settings.topic-in}")
    public void consume(String message) {
        try {
            var orderDto = commonService.stringToOrderDto(message);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
