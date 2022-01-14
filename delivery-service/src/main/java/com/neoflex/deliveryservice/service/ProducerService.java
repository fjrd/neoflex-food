package com.neoflex.deliveryservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, DeliveryMessageDto> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String, DeliveryMessageDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(DeliveryMessageDto deliveryMessageDto) {
        log.info("Add deliver order in kafka {}", deliveryMessageDto);
        kafkaTemplate.send("processed_deliveries", deliveryMessageDto);
    }
}
