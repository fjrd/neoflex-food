package com.example.processorservice.service;

import org.example.dto.order.message.OrderMessageDto;
import org.springframework.stereotype.Service;

@Service
public record OrderProcessServiceImpl() implements OrderProcessService{
    @Override
    public void ordersProcess(OrderMessageDto orderDto) {

    }
}
