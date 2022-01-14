//package com.example.ordersservice.repository;
//
//import com.example.ordersservice.controller.dto.dish.DishRequestDto;
//import com.example.ordersservice.controller.dto.order.OrderRequestDto;
//import com.example.ordersservice.mapper.OrderRequestMapper;
//import com.example.ordersservice.model.Customer;
//import com.example.ordersservice.model.Order;
//import lombok.RequiredArgsConstructor;
//import org.example.dto.payment.message.CardDetailMessageDto;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.UUID;
//
//@Disabled
//@SpringBootTest
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//class DishOrderRepositoryTest {
//
//    private final OrderRepository orderRepository;
//    private final OrderRequestMapper requestMapper;
//
//    private OrderRequestDto orderRequestDto;
//
//    private UUID orderId;
//
//    @BeforeEach
//    void setUp() {
//
//        orderId = UUID.randomUUID();
//
//        orderRequestDto = OrderRequestDto.builder()
//                .orderId(orderId)
//                .deliveryAddress("address")
//                .cardDetails(CardDetailMessageDto.builder()
//                        .cardNumber("cardNumber")
//                        .cardExpireDate("cardExpDate")
//                        .cardVerificationCode("123")
//                        .firstName("Vasya")
//                        .lastName("LastName")
//                        .build())
//                .orderTotalCost(BigDecimal.valueOf(999))
//                .dishesList(List.of(DishRequestDto.builder()
//                        .orderId(orderId)
//                        .dishId(UUID.randomUUID())
//                        .quantity(5)
//                        .build()))
//                .build();
//
//    }
//
//    @Test
//    void save() {
//
//        Order order = requestMapper.dtoToModel(orderRequestDto);
//        order.setCustomer(new Customer(UUID.randomUUID()));
//        System.out.println("_____________________");
//        System.out.println(order);
//        System.out.println("_____________________");
//        System.out.println(order.getDishesList().get(0).getOrder());
//        System.out.println(order.equals(order.getDishesList().get(0).getOrder()));
//        System.out.println("_____________________");
//
//        orderRepository.saveAndFlush(order);
//
//    }
//}