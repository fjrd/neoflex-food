package org.example.dto.delivery.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.dto.delivery.DeliveryStatus;
import org.example.dto.restaurant.RestaurantOrderStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class DeliveryMessageDto implements Serializable {

    @NotNull
    private UUID orderId;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private RestaurantOrderStatus restaurantStatus;

    @NotBlank
    private DeliveryStatus deliveryStatus;

    @NotNull
    private LocalDateTime orderTime;

    private UUID assignedCourierId;

}
