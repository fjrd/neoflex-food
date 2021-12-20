package com.example.processorservice.service.impl;

import com.example.processorservice.service.KafkaProducerService;
import com.example.processorservice.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.order.message.OrderMessageDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.example.dto.payment.message.PaymentStatus.UNPROCESSED;
import static org.example.dto.payment.message.PaymentStatus.values;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {

    private final KafkaProducerService producerService;
    private Random random = new Random();

    @Override
    public void processOrder(OrderMessageDto dto) {
        log.info("processOrder(), dto = {}", dto);

        //this is mocking order processing unless payment, restaurant, delivery services would be developed
        producerService.sendUpdatedOrderToOrdersService(mockStatusProcessing(dto));

    }

    @SneakyThrows
    private OrderMessageDto mockStatusProcessing(OrderMessageDto dto){
        log.info("mockStatusProcessing(), dto = {}", dto);

        Thread.sleep(10000);
        dto.setPaymentStatus(Arrays.stream(values()).filter(ps -> !ps.equals(UNPROCESSED)).skip(random.nextInt(2)).findFirst().get());
        dto.setRestaurantStatus(List.of("COOKING", "DONE").stream().skip(random.nextInt(2)).findFirst().get());
        dto.setDeliveryStatus(List.of("ON_THE_WAY", "SUCCESSFULLY_DELIVERED").stream().skip(random.nextInt(2)).findFirst().get());
        dto.setOrderStatus("IN_PROGRESS");
        return dto;
    }

}
