package com.neoflex.deliveryservice.model.to;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class DeliveryOrderRequestDto {

    @NotNull
    private UUID courierId;
    @NotNull
    private String deliveryStatus;
}
