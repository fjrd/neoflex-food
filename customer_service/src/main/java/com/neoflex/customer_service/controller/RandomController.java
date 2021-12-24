package com.neoflex.customer_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.order.request.OrderRequestDto;
import org.example.dto.order.response.OrderResponseDto;
import org.example.dto.payment.message.PaymentStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(value = CustomerController.REST_URL)
public class RandomController {

    static final String REST_URL = "/auth/";

    @GetMapping("message")
    public String test() {
        return "Hello JavaInUse Called in Second Service";
    }

    @PostMapping("orders")
    public OrderRequestDto postDto(@RequestBody OrderRequestDto requestDto, @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });        return requestDto;
    }

    @GetMapping("orders/{customerId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByCustomerId(@PathVariable UUID customerId){
        log.info("getOrdersByCustomerId(), customerId = {}", customerId);
        List<OrderResponseDto> orderResponseDtoList =
                List.of(
                        new OrderResponseDto(
                                UUID.randomUUID(),
                                customerId,
                                "ordersStatus1",
                                "deliveryAddress1",
                                "dishesList1",
                                PaymentStatus.SUCCESS,
                                "restaurantStatus1",
                                "deliveryStatus1",
                                LocalDateTime.of(2021, 1, 1, 12, 0),
                                114,
                                new BigDecimal(1000)),
                        new OrderResponseDto(
                                UUID.randomUUID(),
                                customerId,
                                "ordersStatus2",
                                "deliveryAddress2",
                                "dishesList2",
                                PaymentStatus.FAIL,
                                "restaurantStatus2",
                                "deliveryStatus2",
                                LocalDateTime.of(2021, 2, 2, 15, 0),
                                114,
                                new BigDecimal(2000)),
                        new OrderResponseDto(
                                UUID.randomUUID(),
                                customerId,
                                "ordersStatus3",
                                "deliveryAddress3",
                                "dishesList3",
                                PaymentStatus.SUCCESS,
                                "restaurantStatus3",
                                "deliveryStatus3",
                                LocalDateTime.of(2021, 3, 3, 21, 0),
                                114,
                                new BigDecimal(1000))
                );

        return ResponseEntity.ok(orderResponseDtoList);
    }

}
