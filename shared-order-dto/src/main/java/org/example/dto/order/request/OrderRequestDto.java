package org.example.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.payment.message.CardDetailDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto implements Serializable {

    @NotNull
    UUID orderId;

    @NotBlank
    String deliveryAddress;

    @NotNull
    CardDetailDto cardDetails;

    @NotBlank
    String dishesList;

    @Min(0)
    @NotNull
    BigDecimal orderAmount;

}