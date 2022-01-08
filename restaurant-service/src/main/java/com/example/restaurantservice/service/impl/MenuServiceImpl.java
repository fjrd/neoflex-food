package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.mapper.DishResponseMapper;
import com.example.restaurantservice.model.Dish;
import com.example.restaurantservice.repository.DishRepository;
import com.example.restaurantservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.restaurant.response.DishResponseDto;
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
    private final DishResponseMapper dishResponseMapper;
    private Map<UUID, Dish> menu;

    @Override
    public void loadMenu() {
        log.info("loadMenu()");
        menu = repository.findAllByIsOnTheMenuContaining(true)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't load menu"))
                .stream()
                .collect(Collectors.toMap(Dish::getDishId, Function.identity()));
    }

    @Override
    public List<DishResponseDto> getMenuAsList() {
        return menu.values()
                .stream()
                .map(dishResponseMapper::modelToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Map<UUID, Dish> getMenuAsMap() {
        return Collections.unmodifiableMap(menu);
    }
}
