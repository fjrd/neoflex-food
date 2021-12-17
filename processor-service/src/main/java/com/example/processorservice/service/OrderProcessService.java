package com.example.processorservice.service;


import org.example.dto.order.message.OrderMessageDto;

public interface OrderProcessService {
     void ordersProcess(OrderMessageDto orderDto);
}
