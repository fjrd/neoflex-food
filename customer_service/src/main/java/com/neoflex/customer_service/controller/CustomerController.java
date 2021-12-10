package com.neoflex.customer_service.controller;

import com.neoflex.customer_service.model.to.CustomerDto;
import com.neoflex.customer_service.model.to.CustomerEditDto;
import com.neoflex.customer_service.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = CustomerController.REST_URL)
public class CustomerController {

    static final String REST_URL = "/auth/";

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("login")
    public CustomerDto authenticate(@RequestBody @Valid CustomerEditDto editDto) {
        log.info("authenticate customer with phone{}", editDto.getPhone());
     return service.get(editDto.getPhone(), editDto.getPassword());
    }

    @PostMapping("register")
    public CustomerDto create(@RequestBody @Valid CustomerEditDto editDto) {
        log.info("create customer {}", editDto);
        return service.create(editDto);
    }
}
