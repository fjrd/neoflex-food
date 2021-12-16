package com.example.ordersservice.repository;

import com.example.ordersservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> getOrdersByCustomerId(UUID customerId);
}
