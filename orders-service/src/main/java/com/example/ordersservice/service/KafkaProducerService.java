package com.example.ordersservice.service;

import dto.FullOrderDto;

public interface KafkaProducerService {

    void send (FullOrderDto dto);

}
