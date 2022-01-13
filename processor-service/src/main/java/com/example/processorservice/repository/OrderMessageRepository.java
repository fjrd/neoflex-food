package com.example.processorservice.repository;

import com.example.processorservice.model.OrderMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderMessageRepository extends CrudRepository<OrderMessage, UUID> {
}
