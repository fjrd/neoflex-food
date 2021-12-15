//package com.example.ordersservice.repository;
//
//import com.example.ordersservice.model.Order;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Disabled
//@SpringBootTest
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//class OrderRepositoryTest {
//
//    private static final String DEFAULT_STATUS = "unprocessed";
//    private final OrderRepository repository;
//    private Order order;
//
//    @BeforeEach
//    void setUp() {
//        order = Order.builder()
//                .customerId(UUID.randomUUID())
//                .cardDetails("card")
//                .dishesList("dishes")
//                .deliveryAddress("address")
//                .build();
//    }
//
//    @Test
//    void saveWorkCorrectlyWhenFieldIsMissing() {
//        Order saved = repository.save(order);
//
//        assertThat(saved)
//                .isNotNull()
//                .matches(o -> o.getCustomerId().equals(order.getCustomerId()))
//                .matches(o -> o.getCardDetails().equals(order.getCardDetails()))
//                .matches(o -> o.getDeliveryAddress().equals(order.getDeliveryAddress()))
//                .matches(o -> o.getDishesList().equals(order.getDishesList()))
//                .matches(o -> o.getOrderStatus().equals(DEFAULT_STATUS))
//                .matches(o -> o.getPaymentStatus().equals(DEFAULT_STATUS))
//                .matches(o -> o.getRestaurantStatus().equals(DEFAULT_STATUS))
//                .matches(o -> o.getDeliveryStatus().equals(DEFAULT_STATUS));
//
//    }
//}