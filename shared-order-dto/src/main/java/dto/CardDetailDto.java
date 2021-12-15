package dto;

public record CardDetailDto(String cardNumber,
                            String validDate,
                            String firstName,
                            String lastName,
                            String cvc) {
}
