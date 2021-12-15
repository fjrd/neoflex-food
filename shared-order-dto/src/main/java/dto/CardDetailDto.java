package dto;

import javax.validation.constraints.NotBlank;

public record CardDetailDto(@NotBlank String cardNumber,
                            @NotBlank String validDate,
                            @NotBlank String firstName,
                            @NotBlank String lastName,
                            @NotBlank String cvc) {
}
