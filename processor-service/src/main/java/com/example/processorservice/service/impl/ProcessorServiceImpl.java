package com.example.processorservice.service.impl;

import com.example.processorservice.mapper.OrderMessageMapper;
import com.example.processorservice.model.OrderMessage;
import com.example.processorservice.repository.OrderMessageRepository;
import com.example.processorservice.service.KafkaProducerService;
import com.example.processorservice.service.ProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.example.dto.order.OrderMessageDto;
import org.example.dto.payment.message.PaymentMessageDto;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessorServiceImpl implements ProcessorService {

    private final KafkaProducerService producerService;
    private final OrderMessageRepository repository;
    private final OrderMessageMapper mapper;

    @Override
    public void processOrder(OrderMessageDto dto) {
        log.info("processOrder(), dto = {}", dto);

        repository.save(mapper.dtoToModel(dto));
        producerService.sendOrderToPaymentService(mapper.orderToPayment(dto));
    }

    @Override
    public void updatePayment(PaymentMessageDto dto) {
        log.info("updatePayment(), dto = {}", dto);

        OrderMessage orderMessage = repository.findById(dto.getOrderId()).orElseThrow();
        orderMessage.setPaymentStatus(dto.getPaymentStatus());
        orderMessage = repository.save(orderMessage);
        OrderMessageDto orderMessageDto = mapper.modelToDto(orderMessage);
        producerService.sendUpdatedOrderToOrdersService(orderMessageDto);

        switch (dto.getPaymentStatus()){
            case SUCCESS -> producerService.sendOrderToRestaurantService(mapper.orderToRestaurantOrder(orderMessageDto));
            case REJECTED, CANCELED -> repository.deleteById(orderMessage.getId());
        }
    }

    @Override
    public void updateRestaurantOrder(RestaurantOrderMessageDto dto) {
        log.info("updateRestaurantOrder(), dto = {}", dto);

        OrderMessage orderMessage = repository.findById(dto.getOrderId()).orElseThrow();
        orderMessage.setRestaurantStatus(dto.getRestaurantStatus());
        orderMessage = repository.save(orderMessage);
        OrderMessageDto orderMessageDto = mapper.modelToDto(orderMessage);
        producerService.sendUpdatedOrderToOrdersService(orderMessageDto);

        switch (dto.getRestaurantStatus()){
            case SUCCESS -> producerService.sendOrderToDeliveryService(mapper.orderToDelivery(orderMessageDto));
            case REJECTED, CANCELED -> producerService.rollbackPayments(mapper.orderToPayment(orderMessageDto));
        }
    }

    @Override
    public void updateDelivery(DeliveryMessageDto dto) {
        log.info("updateDelivery(), dto = {}", dto);

        OrderMessage orderMessage = repository.findById(dto.getOrderId()).orElseThrow();
        orderMessage.setDeliveryStatus(dto.getDeliveryStatus());
        orderMessage = repository.save(orderMessage);
        OrderMessageDto orderMessageDto = mapper.modelToDto(orderMessage);
        producerService.sendUpdatedOrderToOrdersService(orderMessageDto);

        switch (dto.getDeliveryStatus()){
            case SUCCESS -> repository.deleteById(orderMessage.getId());
            case REJECTED -> producerService.rollbackRestaurantOrders(mapper.orderToRestaurantOrder(orderMessageDto));
        }
    }
}
