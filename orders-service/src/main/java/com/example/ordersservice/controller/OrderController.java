package com.example.ordersservice.controller;

import com.example.ordersservice.service.OrderService;
import dto.OrderDto;
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
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(@RequestParam() UUID customerId){
        log.info("getOrdersByCustomerId(), customerId = {}", customerId);
        return ResponseEntity.ok(service.getOrdersByCustomerId(customerId));
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDto){
        log.info("createOrder(), orderDto = {}", orderDto);
        return ResponseEntity.ok(service.createOrUpdateOrder(orderDto));
    }
}
