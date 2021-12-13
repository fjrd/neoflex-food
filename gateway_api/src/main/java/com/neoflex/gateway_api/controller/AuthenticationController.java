package com.neoflex.gateway_api.controller;

import com.neoflex.gateway_api.model.to.CustomerAuthDto;
import com.neoflex.gateway_api.model.to.CustomerDto;
import com.neoflex.gateway_api.service.CustomerService;
import com.neoflex.gateway_api.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final JwtTokenProvider tokenProvider;
    private final CustomerService customerService;

    @Autowired
    public AuthenticationController(JwtTokenProvider tokenProvider, CustomerService customerService) {
        this.tokenProvider = tokenProvider;
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody CustomerAuthDto authDto) {
        CustomerDto customerDto = customerService.getCustomer(authDto);
        String token = tokenProvider.createToken(customerDto.getPhone(), customerDto.getId());
        Map<Object, Object> response = new HashMap<>();
        response.put("phone", authDto.getPhone());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

}
