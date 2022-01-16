package com.neoflex.deliveryservice.controller;

import com.neoflex.deliveryservice.model.to.DeliveryOrderRequestDto;
import com.neoflex.deliveryservice.model.to.DeliveryOrderResponseDto;
import com.neoflex.deliveryservice.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("api/orders")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<DeliveryOrderResponseDto> getAll() {
        log.info("get all orders for delivery");
        return deliveryService.getAll();
    }

    @GetMapping("/{uuid}")
    public DeliveryOrderResponseDto get(@PathVariable UUID uuid) {
        log.info("get order with ud {}", uuid);
        return deliveryService.get(uuid);
    }

    @PutMapping("/{uuid}")
    public void update(@RequestBody DeliveryOrderRequestDto requestDto, @PathVariable UUID uuid) {
        log.info("update delivery order {}", requestDto);
        deliveryService.update(requestDto, uuid);
    }


}
