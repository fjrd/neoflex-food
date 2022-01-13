package com.example.ordersservice.controller.dto.dish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DishResponseDto implements Serializable {

    @NotNull
    private UUID dishId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
