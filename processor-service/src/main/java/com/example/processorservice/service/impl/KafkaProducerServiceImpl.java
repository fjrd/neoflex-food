package com.example.processorservice.service.impl;

import com.example.processorservice.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.example.dto.order.OrderMessageDto;
import org.example.dto.payment.message.PaymentMessageDto;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
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

    private final KafkaTemplate<String, OrderMessageDto> kafkaTemplateOrders;
    private final KafkaTemplate<String, PaymentMessageDto> kafkaTemplatePayments;
    private final KafkaTemplate<String, RestaurantOrderMessageDto> kafkaTemplateRestaurant;
    private final KafkaTemplate<String, DeliveryMessageDto> kafkaTemplateDeliveries;
    private static final String KAFKA_TO_ORDERS_TOPIC = "processed_orders";
    private static final String KAFKA_TO_PAYMENTS_TOPIC = "new_payments";
    private static final String KAFKA_TO_RESTAURANT_TOPIC = "new_restaurant_orders";
    private static final String KAFKA_TO_DELIVERY_TOPIC = "new_deliveries";
    private static final String KAFKA_ROLLBACK_PAYMENTS_TOPIC = "rollback_payments";
    private static final String KAFKA_ROLLBACK_RESTAURANT_ORDERS_TOPIC = "rollback_restaurant_orders";
    private static final String KAFKA_ROLLBACK_DELIVERIES_TOPIC = "rollback_deliveries";
    private static final String SUCCESSFUL_MESSAGE_LOG = "Successful message sending, dto = {}";

    @Override
    public void sendUpdatedOrderToOrdersService(OrderMessageDto dto) {
        log.info("sendUpdatedOrderToOrdersService(), dto = {}", dto);

        ListenableFuture<SendResult<String, OrderMessageDto>> future = kafkaTemplateOrders.send(KAFKA_TO_ORDERS_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent order = {}", dto);
                throw new KafkaException("failure when trying to send a order to processor service");
            }

            @Override
            public void onSuccess(SendResult<String, OrderMessageDto> result) {
                log.info(SUCCESSFUL_MESSAGE_LOG, dto);
            }
        });
    }

    @Override
    public void sendOrderToPaymentService(PaymentMessageDto dto) {
        log.info("sendOrderToPaymentService(), dto = {}", dto);

        ListenableFuture<SendResult<String, PaymentMessageDto>> future = kafkaTemplatePayments.send(KAFKA_TO_PAYMENTS_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent payment message dto = {}", dto);
                throw new KafkaException("failure when trying to send a payment message dto to payment service");
            }

            @Override
            public void onSuccess(SendResult<String, PaymentMessageDto> result) {
                log.info(SUCCESSFUL_MESSAGE_LOG, dto);
            }
        });
    }

    @Override
    public void sendOrderToRestaurantService(RestaurantOrderMessageDto dto) {
        log.info("sendOrderToRestaurantService(), dto = {}", dto);

        ListenableFuture<SendResult<String, RestaurantOrderMessageDto>> future = kafkaTemplateRestaurant.send(KAFKA_TO_RESTAURANT_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent restaurant order message dto = {}", dto);
                throw new KafkaException("failure when trying to send a restaurant message dto to restaurant service");
            }

            @Override
            public void onSuccess(SendResult<String, RestaurantOrderMessageDto> result) {
                log.info(SUCCESSFUL_MESSAGE_LOG, dto);
            }
        });
    }

    @Override
    public void sendOrderToDeliveryService(DeliveryMessageDto dto) {
        log.info("sendOrderToDeliveryService(), dto = {}", dto);

        ListenableFuture<SendResult<String, DeliveryMessageDto>> future = kafkaTemplateDeliveries.send(KAFKA_TO_DELIVERY_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent delivery message dto = {}", dto);
                throw new KafkaException("failure when trying to send a delivery message dto to delivery service");
            }

            @Override
            public void onSuccess(SendResult<String, DeliveryMessageDto> result) {
                log.info(SUCCESSFUL_MESSAGE_LOG, dto);
            }
        });
    }

    @Override
    public void rollbackPayments(PaymentMessageDto dto) {
        log.info("rollbackPayments(), dto = {}", dto);

        ListenableFuture<SendResult<String, PaymentMessageDto>> future = kafkaTemplatePayments.send(KAFKA_ROLLBACK_PAYMENTS_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent payment message dto = {}", dto);
                throw new KafkaException("failure when trying to send a payment message dto to payment service");
            }

            @Override
            public void onSuccess(SendResult<String, PaymentMessageDto> result) {
                log.info(SUCCESSFUL_MESSAGE_LOG, dto);
            }
        });
    }

    @Override
    public void rollbackRestaurantOrders(RestaurantOrderMessageDto dto) {
        log.info("rollbackRestaurantOrders(), dto = {}", dto);

        ListenableFuture<SendResult<String, RestaurantOrderMessageDto>> future = kafkaTemplateRestaurant.send(KAFKA_ROLLBACK_RESTAURANT_ORDERS_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent restaurant order message dto = {}", dto);
                throw new KafkaException("failure when trying to send a restaurant order message dto to restaurant service");
            }

            @Override
            public void onSuccess(SendResult<String, RestaurantOrderMessageDto> result) {
                log.info(SUCCESSFUL_MESSAGE_LOG, dto);
            }
        });
    }

    @Override
    public void rollbackDeliveries(DeliveryMessageDto dto) {
        log.info("rollbackDeliveries(), dto = {}", dto);

        ListenableFuture<SendResult<String, DeliveryMessageDto>> future = kafkaTemplateDeliveries.send(KAFKA_ROLLBACK_DELIVERIES_TOPIC, dto.getOrderId().toString(), dto);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to sent delivery message dto = {}", dto);
                throw new KafkaException("failure when trying to send an delivery message dto to delivery service");
            }

            @Override
            public void onSuccess(SendResult<String, DeliveryMessageDto> result) {
                log.info(SUCCESSFUL_MESSAGE_LOG, dto);
            }
        });
    }
}
