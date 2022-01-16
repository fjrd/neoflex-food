package com.example.processorservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.delivery.DeliveryStatus;
import org.example.dto.order.OrderStatus;
import org.example.dto.payment.PaymentStatus;
import org.example.dto.payment.message.CardDetailMessageDto;
import org.example.dto.restaurant.RestaurantOrderStatus;
import org.example.dto.restaurant.DishMessageDto;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Message")
public class OrderMessage implements Serializable {

    @NotNull
    private UUID id;

    @NotNull
    private UUID customerId;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private LocalDateTime orderTime;

    @NotNull
    private Integer orderCounter;

    @Min(0)
    @NotNull
    private BigDecimal orderTotalCost;

    @NotNull
    private List<DishMessageDto> dishesList;


    @NotNull
    private OrderStatus orderStatus;

    @NotNull
    private PaymentStatus paymentStatus;

    @NotNull
    private RestaurantOrderStatus restaurantStatus;

    @NotNull
    private DeliveryStatus deliveryStatus;


    private UUID assignedCourierId;

    @NotBlank
    private CardDetailMessageDto cardDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMessage that = (OrderMessage) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
