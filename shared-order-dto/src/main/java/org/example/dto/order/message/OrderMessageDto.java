package org.example.dto.order.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.delivery.DeliveryStatus;
import org.example.dto.delivery.message.CourierMessageDto;
import org.example.dto.order.OrderStatus;
import org.example.dto.payment.CardDetailDto;
import org.example.dto.payment.PaymentStatus;
import org.example.dto.restaurant.RestaurantOrderStatus;
import org.example.dto.restaurant.message.DishMessageDto;

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
public class OrderMessageDto implements Serializable {

    @NotNull
    private UUID orderId;

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


    private CourierMessageDto assignedCourier;

    @NotBlank
    private CardDetailDto cardDetails;

}
