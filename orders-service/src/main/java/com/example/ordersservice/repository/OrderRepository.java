package com.example.ordersservice.repository;

import com.example.ordersservice.model.Customer;
import com.example.ordersservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> getAllByCustomer(Customer customer);
    List<Order> getAllByOrderTimeAndOrderCounter(LocalDateTime orderTime, Integer orderCounter);

}
