package com.neoflex.customer_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.order.request.OrderRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = CustomerController.REST_URL)
//@CrossOrigin
public class RandomController {

    static final String REST_URL = "/auth/";

    @GetMapping("message")
    public String test() {
        return "Hello JavaInUse Called in Second Service";
    }

    @PostMapping("order")
    public OrderRequestDto postDto(@RequestBody OrderRequestDto requestDto) {
        System.out.println(requestDto.getOrderId());
        System.out.println(requestDto.getDeliveryAddress());
        System.out.println(requestDto.getCardDetails().cardNumber());
        System.out.println(requestDto.getCardDetails().validDate());
        System.out.println(requestDto.getCardDetails().firstName());
        System.out.println(requestDto.getCardDetails().lastName());
        System.out.println(requestDto.getDishesList());
        System.out.println(requestDto.getOrderAmount());
        return requestDto;
    }

}
