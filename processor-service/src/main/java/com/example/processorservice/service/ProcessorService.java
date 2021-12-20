package com.example.processorservice.service;

import org.example.dto.order.message.OrderMessageDto;

public interface ProcessorService {

    void processOrder(OrderMessageDto dto);


}
