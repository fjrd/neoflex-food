package org.example.dto.restaurant.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DishResponseDto implements Serializable {

    @NotNull
    private UUID dishId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(0)
    @NotNull
    private BigDecimal cost;

}
