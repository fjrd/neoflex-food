package com.neoflex.customer_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = CustomerController.REST_URL)
public class RandomController {

    static final String REST_URL = "/auth/";

    @GetMapping("message")
    public String test() {
        return "Hello JavaInUse Called in Second Service";
    }

}
