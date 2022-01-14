package com.example.payment_service.service.impl;

import com.example.payment_service.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.payment.message.PaymentMessageDto;
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

    private final KafkaTemplate<String, PaymentMessageDto> kafkaTemplate;
    private static final String KAFKA_TO_TOPIC = "processed_payments";

    @Override
    public void send(PaymentMessageDto dto) {
        log.info("send(), dto = {}", dto);
        ListenableFuture<SendResult<String, PaymentMessageDto>> future = kafkaTemplate.send(KAFKA_TO_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent payment = {}", dto);
                throw new KafkaException("failure when trying to send an payment to Payment service");
            }

            @Override
            public void onSuccess(SendResult<String, PaymentMessageDto> result) {
                log.info("Successful message sending, paymentDto = {}", dto);
            }
        });

    }
}
