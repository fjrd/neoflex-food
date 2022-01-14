package com.example.payment_service.service.impl;

import com.example.payment_service.mapper.PaymentMapper;
import com.example.payment_service.model.Payment;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.service.KafkaProducerService;
import com.example.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.payment.PaymentStatus;
import org.example.dto.payment.message.PaymentMessageDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final KafkaProducerService kafkaProducerService;

    @Override
    @SneakyThrows
    public void addNewPayment(PaymentMessageDto dto) {
        log.info("addNewPayment(), dto = {}", dto);
        Payment payment = mapper.dtoToModel(dto);
        AtomicInteger counterRequest = new AtomicInteger(0);

        if (counterRequest.get() == 9) {
            counterRequest.set(0);
            payment.toBuilder()
                    .status(PaymentStatus.FAIL)
                    .build();
        } else {
            Thread.sleep((long) (Math.random() * 50000));
            payment.toBuilder()
                    .status(PaymentStatus.SUCCESS)
                    .build();
        }
        counterRequest.incrementAndGet();

        kafkaProducerService.send(mapper.modelToDTto(payment));
        repository.saveAndFlush(payment);
    }
}
