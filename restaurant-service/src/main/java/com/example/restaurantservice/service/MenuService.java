package com.example.restaurantservice.service;

import com.example.restaurantservice.model.Dish;
import com.example.restaurantservice.controller.dto.response.DishResponseDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MenuService {

    void loadMenu();
    Map<UUID, Dish> getMenuAsMap();
    List<DishResponseDto> getMenuAsList();
}
