package org.example.dto.payment.message;

import javax.validation.constraints.NotBlank;

public record PaymentDto(@NotBlank Integer cardNumber,
                         @NotBlank Integer cvvCode,
                         @NotBlank String cardExpireDate,
                         @NotBlank String cardHolderName,
                         @NotBlank PaymentStatus status) {
}

