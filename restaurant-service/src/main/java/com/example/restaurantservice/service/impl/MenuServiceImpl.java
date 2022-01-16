package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.controller.dto.response.DishResponseDto;
import com.example.restaurantservice.model.Dish;
import com.example.restaurantservice.repository.DishRepository;
import com.example.restaurantservice.service.MenuService;
import com.example.restaurantservice.service.mapper.DishMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final DishRepository repository;
    private final DishMapper mapper;
    private Map<UUID, Dish> menu;

    @Override
    public void loadMenu() {
        log.info("loadMenu()");
        menu = repository.findAllByIsOnTheMenuIsTrue()
                .orElseThrow(() -> new EntityNotFoundException("Couldn't load menu"))
                .stream()
                .collect(Collectors.toMap(Dish::getDishId, Function.identity()));

        log.info("loadMenu(), menu = {}", menu);
    }

    @Override
    public List<DishResponseDto> getMenuAsList() {
        log.info("getMenuAsList()");
        return menu.values().stream()
                .map(mapper::modelToResponseDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Map<UUID, Dish> getMenuAsMap() {
        log.info("getMenuAsMap()");
        return Collections.unmodifiableMap(menu);
    }
}
