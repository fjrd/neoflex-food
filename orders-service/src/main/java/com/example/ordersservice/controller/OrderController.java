package com.example.ordersservice.controller;

import com.example.ordersservice.service.OrderService;
import dto.ClientOrderDto;
import dto.FullOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<List<FullOrderDto>> getOrdersByCustomerId(@PathVariable UUID customerId){
        log.info("getOrdersByCustomerId(), customerId = {}", customerId);
        return ResponseEntity.ok(service.getOrdersByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<FullOrderDto> createOrder(@RequestHeader("x-user-id") UUID customerId, @RequestBody @Valid ClientOrderDto dto){
        log.info("createOrder(), customerId{}, orderDto = {}", customerId, dto);

        return ResponseEntity.ok(service.createOrder(customerId, dto));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<FullOrderDto> updateOrder(@RequestParam UUID orderId, @RequestHeader("x-user-id") UUID customerId, @RequestBody @Valid ClientOrderDto dto){
        log.info("createOrder(), orderId = {}, orderDto = {}", orderId, dto);
        return ResponseEntity.ok(service.updateOrder(orderId, customerId, dto));
    }
}
