package com.neoflex.gateway_api.service;

import com.neoflex.gateway_api.model.to.CustomerAuthDto;
import com.neoflex.gateway_api.model.to.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "customer-service", url = "${restUri}")
public interface CustomerService {

    @PostMapping(value = "/login", consumes = "application/json")
    CustomerDto getCustomer(@RequestBody CustomerAuthDto authDto);
}
