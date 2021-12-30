package org.example.dto.restaurant;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class DishDto {

    @NotNull
    private UUID dish_id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(0)
    @NotNull
    private BigDecimal cost;

    @Min(1)
    @NotNull
    private Integer quantity;

}
