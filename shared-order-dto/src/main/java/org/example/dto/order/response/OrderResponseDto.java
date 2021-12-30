package org.example.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.delivery.DeliveryStatus;
import org.example.dto.order.OrderStatus;
import org.example.dto.payment.PaymentStatus;
import org.example.dto.restaurant.DishDto;
import org.example.dto.restaurant.RestaurantDto;
import org.example.dto.restaurant.RestaurantStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto implements Serializable {

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID customerId;

    @NotNull
    private OrderStatus orderStatus;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private List<DishDto> dishesList;

    @NotNull
    private PaymentStatus paymentStatus;

    @NotNull
    private RestaurantStatus restaurantStatus;

    @NotNull
    private DeliveryStatus deliveryStatus;

    @NotNull
    private LocalDateTime orderTime;

    @NotNull
    private Integer orderCounter;

    @Min(0)
    @NotNull
    private BigDecimal orderTotalCost;

    @NotNull
    private RestaurantDto restaurant;
}
