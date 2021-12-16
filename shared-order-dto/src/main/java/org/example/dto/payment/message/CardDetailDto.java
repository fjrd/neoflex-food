package org.example.dto.payment.message;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder(toBuilder = true)
public record CardDetailDto(@NotBlank String cardNumber,
                            @NotBlank String validDate,
                            @NotBlank String firstName,
                            @NotBlank String lastName,
                            @NotBlank String cvc) {
}
