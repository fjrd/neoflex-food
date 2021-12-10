package com.example.ordersservice.repository;

import com.example.ordersservice.model.Order;
import org.apache.kafka.common.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Uuid> {
    List<Order> getOrdersByCustomerId(UUID customerId);
}
