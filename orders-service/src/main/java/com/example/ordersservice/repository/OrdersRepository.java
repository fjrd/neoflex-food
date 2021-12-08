package com.example.ordersservice.repository;

import com.example.ordersservice.model.Order;
import org.apache.kafka.common.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Uuid> {
}
