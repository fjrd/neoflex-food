package com.example.restaurantservice.controller;

import com.example.restaurantservice.service.MenuService;
import com.example.restaurantservice.service.RestaurantOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.restaurant.request.RestaurantOrderRequestDto;
import org.example.dto.restaurant.response.RestaurantOrderResponseDto;
import org.example.dto.restaurant.request.DishRequestDto;
import org.example.dto.restaurant.response.DishResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/restaurant_orders")
public class RestaurantController {

    private final RestaurantOrderService service;
    private final MenuService menuService;

    @GetMapping("/menu")
    public ResponseEntity<List<DishResponseDto>> getMenu(){
        log.info("getMenu()");
        return ResponseEntity.ok(menuService.getMenuAsList());
    }

    @PostMapping("/checkOrder")
    public ResponseEntity<String> checkOrder(@RequestBody @Valid List<DishRequestDto> dishesList){
        log.info("cherOrder(), dto = {}", dishesList);
        return ResponseEntity.ok(service.checkOrder(dishesList));
    }

    @GetMapping("new_orders")
    public ResponseEntity<List<RestaurantOrderResponseDto>> getNewOrders(){
        log.info("getNewOrders()");
        return ResponseEntity.ok(service.getNewOrders());
    }

    @PutMapping
    public ResponseEntity<RestaurantOrderResponseDto> updateOrder(@RequestBody @Valid RestaurantOrderRequestDto requestDto){
        log.info("updateOrder()");
        return ResponseEntity.ok(service.updateOrder(requestDto));
    }
}
