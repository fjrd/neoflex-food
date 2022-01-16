package com.example.ordersservice.mapper;

import com.example.ordersservice.controller.dto.dish.DishRequestDto;
import com.example.ordersservice.controller.dto.order.OrderRequestDto;
import com.example.ordersservice.model.Dish;
import com.example.ordersservice.model.DishOrder;
import com.example.ordersservice.model.DishOrderPK;
import lombok.RequiredArgsConstructor;
import org.example.dto.delivery.DeliveryStatus;
import org.example.dto.order.OrderStatus;
import org.example.dto.payment.PaymentStatus;
import org.example.dto.payment.message.CardDetailMessageDto;
import org.example.dto.restaurant.RestaurantOrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {OrderRequestMapperImpl.class, DishRequestMapperImpl.class})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class OrderRequestMapperTest {

    private final OrderRequestMapper mapper;
    private OrderRequestDto dto;
    private UUID orderId;
    private String address;
    private UUID dishId;

    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        address = "some address";
        dishId = UUID.randomUUID();

        dto = OrderRequestDto.builder()
                .orderId(orderId)
                .deliveryAddress(address)
                .cardDetails(CardDetailMessageDto.builder()
                        .cardNumber("cardNumber")
                        .cardExpireDate("cardExpDate")
                        .cardVerificationCode("123")
                        .firstName("Vasya")
                        .lastName("LastName")
                        .build())
                .orderTotalCost(BigDecimal.valueOf(999))
                .dishesList(List.of(DishRequestDto.builder()
                        .orderId(orderId)
                        .dishId(dishId)
                        .quantity(5)
                        .build()))
                .build();
    }

    @Test
    public void dtoToModelWorkCorrectly(){
        assertThat(mapper.dtoToModel(dto))
                .isNotNull()
                .matches(order -> order.getOrderId().equals(orderId) &&
                        order.getDeliveryAddress().equals(address) &&
                        Objects.nonNull(order.getOrderTime()) &&
                        Objects.isNull(order.getOrderCounter()) &&
                        order.getOrderTotalCost().equals(BigDecimal.valueOf(999)) &&
                        order.getDishesList().equals(List.of(DishOrder.builder()
                                .id(new DishOrderPK(orderId, dishId))
                                .quantity(5)
                                .dish(new Dish(dishId))
                                .order(order)
                                .build())) &&
                        order.getOrderStatus().equals(OrderStatus.UNPROCESSED) &&
                        order.getPaymentStatus().equals(PaymentStatus.UNPROCESSED) &&
                        order.getRestaurantStatus().equals(RestaurantOrderStatus.UNPROCESSED) &&
                        order.getDeliveryStatus().equals(DeliveryStatus.UNPROCESSED) &&
                        Objects.isNull(order.getCustomer()) &&
                        Objects.isNull(order.getAssignedCourier()));
    }
}