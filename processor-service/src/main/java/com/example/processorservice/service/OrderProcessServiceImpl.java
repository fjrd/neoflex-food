package com.example.processorservice.service;

import dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public record OrderProcessServiceImpl() implements OrderProcessService{
    @Override
    public void ordersProcess(OrderDto orderDto) {

    }
}
