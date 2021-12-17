package com.example.ordersservice.controller;

import com.example.ordersservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.order.request.OrderRequestDto;
import org.example.dto.order.response.OrderResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService service;

    @GetMapping("/{customerId}")

    public ResponseEntity<List<OrderResponseDto>> getOrdersByCustomerId(@PathVariable UUID customerId){
        log.info("getOrdersByCustomerId(), customerId = {}", customerId);
        return ResponseEntity.ok(service.getOrdersByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestHeader("x-user-id") UUID customerId, @RequestBody @Valid OrderRequestDto dto){
        log.info("createOrder(), customerId{}, orderDto = {}", customerId, dto);

        return ResponseEntity.ok(service.createOrder(customerId, dto));
    }

    @PutMapping
    public ResponseEntity<OrderResponseDto> updateOrder(@RequestHeader("x-user-id") UUID customerId, @RequestBody @Valid OrderRequestDto dto){
        log.info("createOrder(), customerId = {}, orderDto = {}", customerId, dto);
        return ResponseEntity.ok(service.updateOrder(customerId, dto));
    }
}
