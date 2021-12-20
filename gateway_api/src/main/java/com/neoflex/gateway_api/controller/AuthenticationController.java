package com.neoflex.gateway_api.controller;

import com.neoflex.gateway_api.model.to.AuthResponse;
import com.neoflex.gateway_api.model.to.CustomerAuthDto;
import com.neoflex.gateway_api.model.to.CustomerDto;
import com.neoflex.gateway_api.service.CustomerService;
import com.neoflex.gateway_api.util.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
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
        log.info("Authenticate customer with phone {}", authDto.getPhone());
        CustomerDto customerDto = customerService.getCustomer(authDto);
        String token = tokenProvider.createToken(customerDto.getPhone(), customerDto.getId());
        return ResponseEntity.ok(new AuthResponse(customerDto.getId(), customerDto.getName(), customerDto.getPhone(), token));
    }
}
