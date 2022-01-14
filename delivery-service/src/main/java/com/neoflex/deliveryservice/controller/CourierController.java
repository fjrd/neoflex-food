package com.neoflex.deliveryservice.controller;

import com.neoflex.deliveryservice.model.Courier;
import com.neoflex.deliveryservice.model.to.CourierDto;
import com.neoflex.deliveryservice.service.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("api/couriers")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @PostMapping
    public void create (@RequestBody @Valid CourierDto courierDto) {
        log.info("save new courier {}", courierDto);
        courierService.create(courierDto);
    }
}
