package com.example.processorservice.service.impl;

import com.example.processorservice.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.order.message.OrderMessageDto;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, OrderMessageDto> kafkaTemplate;
    private static final String KAFKA_TO_ORDERS_TOPIC = "processed_orders";

    @Override
    public void sendUpdatedOrderToOrdersService(OrderMessageDto dto) {
        log.info("sendUpdatedOrderToOrdersService(), dto = {}", dto);

        ListenableFuture<SendResult<String, OrderMessageDto>> future = kafkaTemplate.send(KAFKA_TO_ORDERS_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent order = {}", dto);
                throw new KafkaException("failure when trying to send a order to Processor service");
            }

            @Override
            public void onSuccess(SendResult<String, OrderMessageDto> result) {
                log.info("Successful message sending, orderDto = {}", dto);
            }
        });
    }
}
