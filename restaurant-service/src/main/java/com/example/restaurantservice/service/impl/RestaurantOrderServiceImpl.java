package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.mapper.RestaurantOrderMessageMapper;
import com.example.restaurantservice.mapper.RestaurantOrderRequestMapper;
import com.example.restaurantservice.mapper.RestaurantOrderResponseMapper;
import com.example.restaurantservice.model.DishOrder;
import com.example.restaurantservice.model.RestaurantOrder;
import com.example.restaurantservice.repository.RestaurantOrderRepository;
import com.example.restaurantservice.service.KafkaProducerService;
import com.example.restaurantservice.service.MenuService;
import com.example.restaurantservice.service.RestaurantOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.restaurant.RestaurantOrderStatus;
import org.example.dto.restaurant.message.RestaurantOrderMessageDto;
import org.example.dto.restaurant.request.DishRequestDto;
import org.example.dto.restaurant.request.RestaurantOrderRequestDto;
import org.example.dto.restaurant.response.RestaurantOrderResponseDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantOrderServiceImpl implements RestaurantOrderService {

    private final RestaurantOrderRepository repository;
    private final MenuService menuService;
    private final KafkaProducerService kafkaProducerService;
    private final RestaurantOrderMessageMapper restaurantOrderMessageMapper;
    private final RestaurantOrderResponseMapper restaurantOrderResponseMapper;
    private final RestaurantOrderRequestMapper restaurantOrderRequestMapper;


    @Override
    public List<RestaurantOrderResponseDto> getNewOrders() {
        log.info("getNewOrders()");
        return repository.findAllByRestaurantStatusContaining(RestaurantOrderStatus.UNPROCESSED.toString())
                .orElseThrow(() -> new EntityNotFoundException("Menu is empty"))
                .stream()
                .map(order -> restaurantOrderResponseMapper.modelToDto(order))
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantOrderResponseDto updateOrder(RestaurantOrderRequestDto requestDto) {
        log.info("updateOrder(), requestDto = {}", requestDto);
        RestaurantOrder restaurantOrder = restaurantOrderRequestMapper.dtoToModel(requestDto);
        repository.save(restaurantOrder);
        kafkaProducerService.send(restaurantOrderMessageMapper.modelToDto(restaurantOrder));
        return restaurantOrderResponseMapper.modelToDto(restaurantOrder);
    }

    @Override
    public void addNewOrder(RestaurantOrderMessageDto dto) {
        log.info("addNewOrder(), dto = {}", dto);
        RestaurantOrder restaurantOrder = restaurantOrderMessageMapper.dtoToModel(dto);
        restaurantOrder.setDishes(dto.getDishesList()
                .stream()
                .map(dish -> DishOrder.builder()
                        .quantity(dish.getQuantity())
                        .dish(menuService.getMenuAsMap().get(dish.getDishId()))
                        .restaurantOrder(restaurantOrder)
                        .build())
                .collect(Collectors.toList()));
        repository.save(restaurantOrder);
    }

    @Override
    public String checkOrder(List<DishRequestDto> dishesList) {
        log.info("checkOrder(), dishesList = {}", dishesList);
        dishesList.stream()
                .filter(dish -> menuService.getMenuAsMap().get(dish.getDishId()).getCost().compareTo(dish.getCost()) != 0)
                .findAny()
                .ifPresent(dish -> {throw new IllegalArgumentException("Dish with id " + dish.getDishId() + " has wrong parameters");});
        return "Order is correct";
    }
}
