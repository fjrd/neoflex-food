package org.example.dto.order.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.payment.message.CardDetailDto;
import org.example.dto.payment.message.PaymentStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessageDto implements Serializable {

    @NotNull
    UUID orderId;

    @NotNull
    UUID customerId;

    @NotBlank
    String orderStatus;

    @NotBlank
    String deliveryAddress;

    @NotBlank
    CardDetailDto cardDetails;

    @NotBlank
    String dishesList;

    @NotNull
    PaymentStatus paymentStatus;

    @NotBlank
    String restaurantStatus;

    @NotBlank
    String deliveryStatus;

    @NotNull
    LocalDateTime orderTime;

    @NotNull
    Integer orderCounter;

    @Min(0)
    @NotNull
    BigDecimal orderAmount;
}
