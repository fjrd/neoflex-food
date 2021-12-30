package org.example.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CardDetailDto{

    @NotBlank
    String cardNumber;

    @NotBlank
    String cardExpireDate;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String cardVerificationCode;

}
