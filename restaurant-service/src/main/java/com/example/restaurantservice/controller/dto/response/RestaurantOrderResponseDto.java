package com.example.restaurantservice.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.payment.PaymentStatus;
import org.example.dto.restaurant.RestaurantOrderStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOrderResponseDto {

    @NotNull
    private UUID restaurantOrderId;

    @NotNull
    private UUID customerId;

    @NotNull
    private LocalDateTime orderTime;

    @NotNull
    private Integer orderCounter;

    @Min(0)
    @NotNull
    private BigDecimal orderTotalCost;

    @NotBlank
    private List<DishOrderResponseDto> dishesList;

    @NotNull
    private PaymentStatus paymentStatus;

    @NotNull
    private RestaurantOrderStatus restaurantStatus;

}
