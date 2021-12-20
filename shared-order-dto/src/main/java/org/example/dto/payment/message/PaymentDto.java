package org.example.dto.payment.message;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Builder(toBuilder = true)
public record PaymentDto(@NotBlank String cardNumber,
                         @NotBlank String cvvCode,
                         @NotBlank String cardExpireDate,
                         @NotBlank String firstName,
                         @NotBlank String lastName,
                         @NotBlank PaymentStatus status,
                         @NotBlank BigDecimal orderAmount) {
}

