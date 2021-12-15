package dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record CardDetailDto(@NotBlank String cardNumber,
                            @NotBlank String validDate,
                            @NotBlank String firstName,
                            @NotBlank String lastName,
                            @NotBlank String cvc) {
}
