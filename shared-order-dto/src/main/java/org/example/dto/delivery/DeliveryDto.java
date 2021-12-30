package org.example.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.dto.restaurant.DishDto;
import org.example.dto.restaurant.RestaurantDto;
import org.example.dto.restaurant.RestaurantStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class DeliveryDto {

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID customerId;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private List<DishDto> dishesList;

    @NotNull
    private RestaurantStatus restaurantStatus;

    @NotBlank
    private DeliveryStatus deliveryStatus;

    @NotNull
    private LocalDateTime orderTime;

    @NotNull
    private RestaurantDto restaurant;
}
