package com.neoflex.deliveryservice.model.to;

import lombok.Data;
import org.example.dto.restaurant.RestaurantOrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DeliveryOrderResponseDto {
    private UUID orderId;
    private String deliveryAddress;
    private RestaurantOrderStatus restaurantStatus;
    private LocalDateTime orderTime;
}
