package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.controller.dto.request.DishRequestDto;
import com.example.restaurantservice.controller.dto.request.RestaurantOrderRequestDto;
import com.example.restaurantservice.controller.dto.response.RestaurantOrderResponseDto;
import com.example.restaurantservice.model.RestaurantOrder;
import com.example.restaurantservice.repository.DishRepository;
import com.example.restaurantservice.repository.RestaurantOrderRepository;
import com.example.restaurantservice.service.KafkaProducerService;
import com.example.restaurantservice.service.MenuService;
import com.example.restaurantservice.service.RestaurantOrderService;
import com.example.restaurantservice.service.mapper.RestaurantOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.restaurant.RestaurantOrderMessageDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.dto.restaurant.RestaurantOrderStatus.REJECTED;
import static org.example.dto.restaurant.RestaurantOrderStatus.UNPROCESSED;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantOrderServiceImpl implements RestaurantOrderService {

    private final RestaurantOrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final MenuService menuService;
    private final KafkaProducerService kafkaProducerService;
    private final RestaurantOrderMapper mapper;


    @Override
    public List<RestaurantOrderResponseDto> getNewOrders() {
        log.info("getNewOrders()");

        return orderRepository.findAllByRestaurantStatus(UNPROCESSED)
                .orElseThrow(() -> new EntityNotFoundException("No new orders"))
                .stream()
                .map(mapper::modelToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantOrderResponseDto updateOrder(RestaurantOrderRequestDto requestDto) {
        log.info("updateOrder(), requestDto = {}", requestDto);

        RestaurantOrder restaurantOrder = orderRepository.findById(requestDto.getRestaurantOrderId())
                .orElseThrow(() -> new EntityNotFoundException("There is no such order with ID = " + requestDto.getRestaurantOrderId()));
        restaurantOrder.setRestaurantStatus(requestDto.getRestaurantStatus());
        restaurantOrder = orderRepository.save(restaurantOrder);

        kafkaProducerService.send(mapper.modelToMessageDto(restaurantOrder));
        return mapper.modelToResponseDto(restaurantOrder);
    }

    @Override
    public void addNewOrder(RestaurantOrderMessageDto dto) {
        log.info("addNewOrder(), dto = {}", dto);

        RestaurantOrder restaurantOrder = mapper.messageDtoToModel(dto);

        if (checkThatDishesFromOrderIsOnMenu(restaurantOrder) && checkThatOrderTotalCostIsCorrect(restaurantOrder)){
            restaurantOrder.getDishesList().forEach(dishOrder -> dishOrder.setDish(menuService.getMenuAsMap().get(dishOrder.getId().getDishId())));
            orderRepository.save(restaurantOrder);
        }
        else kafkaProducerService.send(mapper.modelToMessageDto(restaurantOrder.toBuilder().restaurantStatus(REJECTED).build()));
    }

    @Override
    public String checkOrder(List<DishRequestDto> dishesList) {
        log.info("checkOrder(), dishesList = {}", dishesList);
        dishesList.stream()
                .filter(dish -> menuService.getMenuAsMap().get(dish.getDishId()).getCost().compareTo(dish.getCost()) != 0)
                .findAny()
                .ifPresent(dish -> {throw new IllegalArgumentException("Dish with id " + dish.getDishId() + " has wrong cost");});
        return "Order is correct";
    }

    boolean checkThatDishesFromOrderIsOnMenu(RestaurantOrder restaurantOrder){
        log.info("checkThatOrderDishesIsOnMenu(), restaurantOrder = {}", restaurantOrder);
        return restaurantOrder.getDishesList()
                .stream()
                .map(dishOrder -> menuService.getMenuAsMap().containsKey(dishOrder.getId().getDishId()))
                .filter(aBoolean -> aBoolean == Boolean.FALSE)
                .findAny().orElse(Boolean.TRUE);
    }

    boolean checkThatOrderTotalCostIsCorrect(RestaurantOrder restaurantOrder){
        log.info("checkThatOrderTotalCostIsCorrect(), restaurantOrder = {}", restaurantOrder);
        return restaurantOrder.getDishesList()
                .stream()
                .map(dishOrder -> menuService.getMenuAsMap()
                        .get(dishOrder
                                .getId()
                                .getDishId())
                        .getCost()
                        .multiply(BigDecimal.valueOf(dishOrder.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .compareTo(restaurantOrder.getOrderTotalCost()) == 0;
    }
}
