package com.example.restaurantservice.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DishOrderResponseDto implements Serializable {

    @NotNull
    private UUID dishId;

    @NotNull
    private Integer quantity;

}
