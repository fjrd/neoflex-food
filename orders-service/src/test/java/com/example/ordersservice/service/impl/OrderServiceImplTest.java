package com.example.ordersservice.service.impl;

import com.example.ordersservice.mapper.OrderMessageMapper;
import com.example.ordersservice.mapper.OrderRequestMaper;
import com.example.ordersservice.mapper.OrderResponseMapper;
import com.example.ordersservice.model.Order;
import com.example.ordersservice.repository.OrderRepository;
import com.example.ordersservice.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.dto.order.message.OrderMessageDto;
import org.example.dto.order.request.OrderRequestDto;
import org.example.dto.order.response.OrderResponseDto;
import org.example.dto.payment.message.CardDetailDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.GeneratedValue;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class OrderServiceImplTest {

    private final OrderMessageMapper orderMessageMapper;
    private final OrderRequestMaper orderRequestMaper;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderServiceImpl service;

    @MockBean
    private KafkaProducerService producer;
    @MockBean
    private OrderRepository repository;

    private static final String ADDRESS = "address";
    private static final String DISHES = "3 cheesy pizzas";
    private static final String DEFAULT_STATUS = "unprocessed";
    private static final String CARD_NUMBER = "1234567890";
    private static final String EXPIRE_DATE = "11/12";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String CVC = "123";

    private UUID customerId;
    private UUID orderId;
    private CardDetailDto cardDetails;
    private OrderRequestDto requestDto;
    private Order order;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();
        orderId = UUID.randomUUID();

        cardDetails = new CardDetailDto(CARD_NUMBER, EXPIRE_DATE, FIRST_NAME, LAST_NAME, CVC);

        requestDto = OrderRequestDto.builder()
                .deliveryAddress(ADDRESS)
                .dishesList(DISHES)
                .cardDetails(cardDetails)
                .build();

        order = orderRequestMaper.dtoToModel(requestDto);

        Mockito.when(repository.save(any(Order.class))).thenAnswer(i -> {
            Object argument = i.getArguments()[0];
            Stream.of(argument.getClass().getDeclaredFields())
                    .filter(f -> f.getAnnotation(GeneratedValue.class) != null && f.getType().equals(UUID.class))
                    .forEach(f -> {
                        try {
                            f.setAccessible(true);
                            f.set(argument, orderId);
                            f.setAccessible(false);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
            return argument;
        });
    }

    @Test
    void createOrderWorkCorrectlyTest() {
        assertThat(service.createOrder(customerId, requestDto))
                .isNotNull()
                .isExactlyInstanceOf(OrderResponseDto.class)
                .matches(o -> o.getOrderId().equals(orderId))
                .matches(o -> o.getCustomerId().equals(customerId))
                .matches(o -> o.getDeliveryAddress().equals(ADDRESS))
                .matches(o -> o.getDishesList().equals(DISHES))
                .matches(o -> o.getOrderStatus().equals(DEFAULT_STATUS))
                .matches(o -> o.getPaymentStatus().equals(DEFAULT_STATUS))
                .matches(o -> o.getRestaurantStatus().equals(DEFAULT_STATUS))
                .matches(o -> o.getDeliveryStatus().equals(DEFAULT_STATUS));

        Mockito.verify(repository, Mockito.times(1)).save(any(Order.class));
        Mockito.verify(producer, Mockito.times(1)).send(any(OrderMessageDto.class));
    }
}