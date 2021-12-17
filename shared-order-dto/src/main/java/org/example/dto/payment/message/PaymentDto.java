package org.example.dto.payment.message;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.BigInteger;

@Builder(toBuilder = true)
public record PaymentDto(@NotBlank Integer cardNumber,
                         @NotBlank Integer cvvCode,
                         @NotBlank String cardExpireDate,
                         @NotBlank String cardHolderName,
                         @NotBlank PaymentStatus status,
                         @NotBlank BigDecimal orderAmount ) {
}

