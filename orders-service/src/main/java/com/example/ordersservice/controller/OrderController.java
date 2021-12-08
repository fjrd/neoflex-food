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
        //TODO
        return null;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestParam UUID customerId, @RequestBody @Valid OrderDto orderDto){
        log.info("createOrder(), customerId = {}, orderDto = {}", customerId, orderDto);
        //TODO
        return null;
    }

    @PutMapping("/customerId")
    public ResponseEntity<OrderDto> updateOrder(@RequestParam UUID customerId, @RequestBody @Valid OrderDto orderDto){
        log.info("updateOrder(), customerId = {}, orderDto = {}", customerId, orderDto);
        //TODO
        return null;
    }

}
