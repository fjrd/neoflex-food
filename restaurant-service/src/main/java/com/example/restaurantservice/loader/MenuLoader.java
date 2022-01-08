package com.example.restaurantservice.loader;

import com.example.restaurantservice.model.Dish;
import com.example.restaurantservice.repository.DishRepository;
import com.example.restaurantservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class MenuLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final DishRepository repository;
    private final MenuService service;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<Dish> menu = Stream.of(
                Dish.builder()
                        .name("Pizza 4 season")
                        .description("n/a")
                        .cost(BigDecimal.valueOf(444))
                        .isOnTheMenu(true)
                        .build(),
                Dish.builder()
                        .name("Pizza 5 cheeses")
                        .description("n/a")
                        .cost(BigDecimal.valueOf(555))
                        .isOnTheMenu(true)
                        .build(),
                Dish.builder()
                        .name("Pizza pepperoni")
                        .description("n/a")
                        .cost(BigDecimal.valueOf(666))
                        .isOnTheMenu(true)
                        .build())
                .collect(Collectors.toList());

        repository.saveAll(menu);
        service.loadMenu();
    }
}
