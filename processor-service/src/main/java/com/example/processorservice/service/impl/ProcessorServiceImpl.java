package com.example.processorservice.service.impl;

import com.example.processorservice.service.KafkaProducerService;
import com.example.processorservice.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.payment.message.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {

    private final KafkaProducerService producerService;
    private Random random = new Random();

    @Override
    public void processOrder(OrderMessageDto dto) {
        log.info("processOrder(), dto = {}", dto);
        mockStatusProcessing(dto);
    }

    //this method is mocking order processing unless payment, restaurant, delivery services would be developed
    @SneakyThrows
    private void mockStatusProcessing(OrderMessageDto dto){
        log.info("mockStatusProcessing(), dto = {}", dto);

        Thread.sleep(5000);
        dto.setPaymentStatus(PaymentStatus.SUCCESS);
        dto.setOrderStatus("IN_PROGRESS");
        producerService.sendUpdatedOrderToOrdersService(dto);

        Thread.sleep(5000);
        dto.setRestaurantStatus("COOKING_IN_PROGRESS");
        producerService.sendUpdatedOrderToOrdersService(dto);

        Thread.sleep(5000);
        dto.setRestaurantStatus("COOKED");
        producerService.sendUpdatedOrderToOrdersService(dto);

        Thread.sleep(5000);
        dto.setDeliveryStatus("DELIVERY_IN_PROGRESS");
        producerService.sendUpdatedOrderToOrdersService(dto);

        Thread.sleep(5000);
        dto.setDeliveryStatus("DELIVERED");
        dto.setOrderStatus("COMPLETED");
        producerService.sendUpdatedOrderToOrdersService(dto);

    }

}
