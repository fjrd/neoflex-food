package com.example.ordersservice.repository;

import com.example.ordersservice.model.DishOrder;
import com.example.ordersservice.model.DishOrderPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishOrderRepository extends JpaRepository<DishOrder, DishOrderPK> {
}