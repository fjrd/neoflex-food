package com.example.processorservice.service.impl;

import com.example.processorservice.service.KafkaProducerService;
import com.example.processorservice.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.delivery.DeliveryStatus;
import org.example.dto.order.OrderStatus;
import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.payment.PaymentStatus;
import org.example.dto.restaurant.RestaurantOrderStatus;
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
        dto.setOrderStatus(OrderStatus.IN_PROGRESS);
        producerService.sendUpdatedOrderToOrdersService(dto);

        Thread.sleep(5000);
        dto.setRestaurantStatus(RestaurantOrderStatus.COOKING);
        producerService.sendUpdatedOrderToOrdersService(dto);

        Thread.sleep(5000);
        dto.setRestaurantStatus(RestaurantOrderStatus.DONE);
        producerService.sendUpdatedOrderToOrdersService(dto);

        Thread.sleep(5000);
        dto.setDeliveryStatus(DeliveryStatus.IN_PROGRESS);
        producerService.sendUpdatedOrderToOrdersService(dto);

        Thread.sleep(5000);
        dto.setDeliveryStatus(DeliveryStatus.SUCCESS);
        dto.setOrderStatus(OrderStatus.COMPLETED);
        producerService.sendUpdatedOrderToOrdersService(dto);

    }

}
