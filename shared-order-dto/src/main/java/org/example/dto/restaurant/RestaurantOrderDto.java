package org.example.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.dto.payment.PaymentStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class RestaurantOrderDto {

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID customerId;

    @NotBlank
    private List<DishDto> dishesList;

    @NotNull
    private PaymentStatus paymentStatus;

    @NotNull
    private RestaurantStatus restaurantStatus;

    @NotNull
    private LocalDateTime orderTime;

    @NotNull
    private RestaurantDto restaurant;

}
