package com.example.ordersservice.service;

import dto.FullOrderDto;

public interface KafkaConsumerService {

    void loadProcessedOrder(FullOrderDto dto);

}
