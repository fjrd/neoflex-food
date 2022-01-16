package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.controller.dto.response.DishResponseDto;
import com.example.restaurantservice.model.Dish;
import com.example.restaurantservice.repository.DishRepository;
import com.example.restaurantservice.service.mapper.DishMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

    @Mock private DishMapper mapper;
    @Mock private DishRepository repository;
    @InjectMocks private MenuServiceImpl service;

    private List<Dish> dishList;
    private List<DishResponseDto> dishResponseDtoList;

    private UUID dishId1;
    private UUID dishId2;
    private Dish dish1;
    private Dish dish2;
    private DishResponseDto dto1;
    private DishResponseDto dto2;


    @BeforeEach
    void setUp() {

        dishId1 = UUID.randomUUID();
        dishId2 = UUID.randomUUID();
        dish1 = Dish.builder()
                .dishId(dishId1)
                .name("pizza")
                .description("descr1")
                .cost(BigDecimal.valueOf(123))
                .isOnTheMenu(Boolean.TRUE)
                .build();
        dish2 = Dish.builder()
                .dishId(dishId2)
                .name("burger")
                .description("descr2")
                .cost(BigDecimal.valueOf(456))
                .isOnTheMenu(Boolean.TRUE)
                .build();
        dto1 = DishResponseDto.builder()
                .dishId(dishId1)
                .name("pizza")
                .description("descr1")
                .cost(BigDecimal.valueOf(123))
                .build();
        dto2 = DishResponseDto.builder()
                .dishId(dishId1)
                .name("burger")
                .description("descr2")
                .cost(BigDecimal.valueOf(456))
                .build();
        dishList = List.of(dish1, dish2);
        dishResponseDtoList = List.of(dto1, dto2);

        Mockito.lenient().when(repository.findAllByIsOnTheMenuIsTrue()).thenAnswer(invocation -> Optional.of(dishList));
        Mockito.lenient().when(mapper.modelToResponseDto(dish1)).thenReturn(dto1);
        Mockito.lenient().when(mapper.modelToResponseDto(dish2)).thenReturn(dto2);

    }

    @Test
    void loadMenuShouldThrowENFWhenMenuIsEmpty() {
        Mockito.when(repository.findAllByIsOnTheMenuIsTrue()).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.loadMenu());
    }

    @Test
    void getMenuAsListShouldReturnCorrectDtoList() {
        service.loadMenu();
        assertThat(service.getMenuAsList())
                .isNotNull()
                .hasSameElementsAs(dishResponseDtoList);
    }


    @Test
    void getMenuAsMapShouldReturnCorrectDishList() {
        service.loadMenu();
        assertThat(service.getMenuAsMap())
                .isNotNull()
                .containsValues(dish1, dish2);
    }
}
