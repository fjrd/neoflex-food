package com.example.restaurantservice.repository;

import com.example.restaurantservice.model.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder, UUID> {
}