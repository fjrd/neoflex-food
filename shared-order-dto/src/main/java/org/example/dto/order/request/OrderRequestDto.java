package org.example.dto.order.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.payment.message.CardDetailDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto implements Serializable {

    @NotBlank
    String deliveryAddress;

    @NotNull
    CardDetailDto cardDetails;

    @NotBlank
    String dishesList;

}