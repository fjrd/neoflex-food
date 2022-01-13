//package com.example.ordersservice.mapper;
//
//import com.example.ordersservice.model.Customer;
//import com.example.ordersservice.model.Order;
//import lombok.RequiredArgsConstructor;
//import org.example.dto.order.request.OrderRequestDto;
//import org.example.dto.payment.message.CardDetailMessageDto;
//import org.example.dto.restaurant.request.DishRequestDto;
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
//@SpringBootTest(classes = {OrderRequestMapperImpl.class, DishRequestToDishOrderMapperImpl.class})
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//class OrderRequestMapperTest {
//
//    private final OrderRequestMapper mapper;
//    private OrderRequestDto dto;
//    private UUID customerId;
//
//    @BeforeEach
//    void setUp() {
//        customerId = UUID.randomUUID();
//
//        dto = OrderRequestDto.builder()
//                .orderId(UUID.randomUUID())
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
//                        .orderId(UUID.randomUUID())
//                        .dishId(UUID.randomUUID())
//                        .cost(BigDecimal.valueOf(123))
//                        .quantity(5)
//                        .build()))
//                .build();
//    }
//
//    @Test
//    public void mapperTest(){
//        Order order = mapper.dtoToModel(dto);
//        order = order.toBuilder()
//                .customer(new Customer(customerId))
//                .build();
//        System.out.println("__________________");
//        System.out.println(order);
//        System.out.println("__________________");
//        System.out.println("dish " + order.getDishesList().get(0).getDish().getDishId());
//        System.out.println("__________________");
//        System.out.println("order " + order.getDishesList().get(0).getOrder().getOrderId());
//        System.out.println("__________________");
//    }
//
//}