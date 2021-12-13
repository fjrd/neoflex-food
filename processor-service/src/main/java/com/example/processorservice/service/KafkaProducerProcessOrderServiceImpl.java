package com.example.processorservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record KafkaProducerProcessOrderServiceImpl(KafkaTemplate<UUID, String> kafkaTemplate)
        implements KafkaProducerProcessOrderService {

    @Value("${kafka-manual-settings.topic-out}")
    private static String topic;

    @Override
    public void produce(String orderDto) {
        kafkaTemplate.send(topic, orderDto);
    }
}
