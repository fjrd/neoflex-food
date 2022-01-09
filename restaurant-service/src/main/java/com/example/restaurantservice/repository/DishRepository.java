package com.example.restaurantservice.repository;

import com.example.restaurantservice.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishRepository extends JpaRepository<Dish, UUID> {

    Optional<List<Dish>> findAllByIsOnTheMenuContaining(Boolean isOnTheMenu);

}
