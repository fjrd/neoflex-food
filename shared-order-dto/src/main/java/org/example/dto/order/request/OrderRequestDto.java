package org.example.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.payment.CardDetailDto;
import org.example.dto.restaurant.request.DishRequestDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto implements Serializable {

    @NotNull
    private UUID orderId;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private CardDetailDto cardDetails;

    @NotNull
    private List<DishRequestDto> dishesList;

    @Min(0)
    @NotNull
    private BigDecimal orderTotalCost;

}
