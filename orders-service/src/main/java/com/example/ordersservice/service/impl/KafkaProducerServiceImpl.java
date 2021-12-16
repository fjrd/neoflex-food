package com.example.ordersservice.service.impl;

import com.example.ordersservice.service.KafkaProducerService;
import org.example.dto.order.message.OrderMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<UUID, OrderMessageDto> kafkaTemplate;
    private static final String KAFKA_TO_TOPIC = "new_orders";

    public void send(OrderMessageDto dto) {
        log.info("sendToProcessorByKafka(), orderDto = {}", dto);

        ListenableFuture<SendResult<UUID, OrderMessageDto>> future = kafkaTemplate.send(KAFKA_TO_TOPIC, dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent order = {}", dto);
                throw new KafkaException("failure when trying to send a order to Processor service");
            }

            @Override
            public void onSuccess(SendResult<UUID, OrderMessageDto> result) {
                log.info("Successful message sending, orderDto = {}", dto);
            }
        });
    }
}
