package dto;

public record PaymentDto(Integer cardNumber,
                         Integer cvvCode,
                         String cardExpireDate,
                         String cardHolderName,
                         PaymentStatus status) {
}
