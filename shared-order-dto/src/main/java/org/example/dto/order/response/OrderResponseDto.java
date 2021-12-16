package org.example.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto implements Serializable {

    @NotNull
    UUID id;

    @NotNull
    UUID customerId;

    @NotBlank
    String orderStatus;

    @NotBlank
    String deliveryAddress;

    @NotBlank
    String dishesList;

    @NotBlank
    String paymentStatus;

    @NotBlank
    String restaurantStatus;

    @NotBlank
    String deliveryStatus;
}
