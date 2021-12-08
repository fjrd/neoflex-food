import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class OrderDto {

    @NotNull
    UUID id;

    @NotNull
    UUID customerId;

    @NotBlank
    String orderStatus;

    @NotBlank
    String deliveryAddress;

    @NotBlank
    String cardDetails;

    @NotBlank
    String dishesList;

    @NotBlank
    String paymentStatus;

    @NotBlank
    String restaurantStatus;

    @NotBlank
    String deliveryStatus;
}
