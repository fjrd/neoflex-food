package com.neoflex.deliveryservice.service;


import lombok.extern.slf4j.Slf4j;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    private final DeliveryService deliveryService;

    public ConsumerService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @KafkaListener(
            topics = "new_deliveries",
            groupId = "${spring.kafka.consumer.groupId}",
            containerFactory = "applicationKafkaListenerContainerFactory")
    public void consume(DeliveryMessageDto messageDto) {
        log.info("consume order for delivery {}", messageDto);
        deliveryService.create(messageDto);
    }
}
