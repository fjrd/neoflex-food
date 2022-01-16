package com.example.processorservice.service.impl;

import com.example.processorservice.model.OrderMessage;
import com.example.processorservice.repository.OrderMessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Disabled
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ProcessorServiceImplTest {

    private final OrderMessageRepository repository;
    private OrderMessage message;

    @BeforeEach
    void setUp() {
        message = OrderMessage.builder().id(UUID.randomUUID()).build();
    }

    @Test
    void test() throws JsonProcessingException {

        System.out.println(repository.save(message));
        System.out.println(repository.findById(message.getId()));

    }
}