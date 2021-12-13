package com.example.ordersservice.service;

import com.example.ordersservice.mapper.OrderMapper;
import com.example.ordersservice.model.Order;
import com.example.ordersservice.repository.OrderRepository;
import dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@CacheConfig(cacheNames = "orders")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper mapper;
    private final OrderRepository repository;
    private final KafkaTemplate<UUID, OrderDto> kafkaTemplate;
    private static final String TOPIC_TO_PROCESSOR = "new_orders";
    private static final String TOPIC_FROM_PROCESSOR = "processed_orders";

    @Override
    @Cacheable
    public List<OrderDto> getOrdersByCustomerId(UUID customerId) {
        log.info("getOrdersByCustomersId(), customerId = {}", customerId);

        return repository.getOrdersByCustomerId(customerId)
                .stream()
                .map(mapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto createOrUpdateOrder(OrderDto orderDto) {
        log.info("createOrder(), orderDto = {}", orderDto);

        OrderDto savedOrderDto = saveOrderToDbAndCache(orderDto);
        sendToProcessorServiceByKafka(TOPIC_TO_PROCESSOR, savedOrderDto);
        return savedOrderDto;
    }

    @CachePut
    public OrderDto saveOrderToDbAndCache(OrderDto orderDto) {
        log.info("createOrder(), dto = {}", orderDto);

        Order savedOrder = repository.save(mapper.dtoToModel(orderDto));
        return mapper.modelToDto(savedOrder);
    }

    private void sendToProcessorServiceByKafka(String topic, OrderDto orderDto) {
        log.info("sendToProcessorByKafka(), topic = {}, orderDto = {}", topic, orderDto);

        ListenableFuture<SendResult<UUID, OrderDto>> future = kafkaTemplate.send(TOPIC_TO_PROCESSOR, orderDto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent order = {}", orderDto);
                throw new KafkaException("failure when trying to send a order to Processor service");
            }

            @Override
            public void onSuccess(SendResult<UUID, OrderDto> result) {
                log.info("Successful message sending, orderDto = {}", orderDto);
            }

        });
    }

    @KafkaListener(topics = TOPIC_FROM_PROCESSOR, groupId = "orders")
    private void loadProcessedOrder(OrderDto orderDto) {
        log.info("loadProcessedOrder(), orderDto = {}", orderDto);

        saveOrderToDbAndCache(orderDto);
    }
}
