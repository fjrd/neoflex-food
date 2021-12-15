package com.example.processorservice.service;

import com.example.processorservice.mapper.OrderDtoMapper;
import dto.OrderDto;
import dto.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public class ProcessorServiceImpl implements ProcessorService{
    @Override
    public void processOrders(OrderDto orderDto) {
        var orderDtoMap = OrderDtoMapper.paymentToOrder(orderDto, );
    }

    @Override
    public void processPayments(PaymentDto paymentDto) {

    }
}