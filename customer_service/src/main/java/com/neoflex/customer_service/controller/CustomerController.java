package com.neoflex.customer_service.controller;

import com.neoflex.customer_service.model.to.CustomerAuthDto;
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
    public CustomerDto authenticate(@RequestBody @Valid CustomerAuthDto authDto) {
        log.info("Authenticate customer with phone {}", authDto.getPhone());
        return service.get(authDto.getPhone(), authDto.getPassword());
    }

    @PostMapping("register")
    public CustomerDto create(@RequestBody @Valid CustomerEditDto editDto) {
        log.info("Create customer {}", editDto);
        return service.create(editDto);
    }
}
