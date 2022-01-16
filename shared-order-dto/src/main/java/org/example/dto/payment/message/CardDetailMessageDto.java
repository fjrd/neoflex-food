package org.example.dto.payment.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CardDetailMessageDto implements Serializable {

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
