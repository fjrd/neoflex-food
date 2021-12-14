package com.example.processorservice.service;

import com.example.processorservice.service.common.CommonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import dto.OrderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record KafkaProducerServiceImpl(KafkaTemplate<UUID, String> kafkaTemplate,
                                       CommonService commonService)
        implements KafkaProducerService {

    @Value("${kafka-manual-settings.topic-out}")
    private static String topic;

    @Override
    public void produce(OrderDto orderDto) {

        try {
            kafkaTemplate.send(topic, orderDto.getCustomerId(), commonService.objectToString(orderDto));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
