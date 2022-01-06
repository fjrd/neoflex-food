package org.example.dto.payment.message;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.dto.payment.CardDetailDto;
import org.example.dto.payment.PaymentStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class PaymentMessageDto implements Serializable {

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID customerId;

    @NotBlank
    private CardDetailDto cardDetails;

    @NotNull
    private PaymentStatus paymentStatus;

    @Min(0)
    @NotNull
    private BigDecimal orderTotalCost;

}
