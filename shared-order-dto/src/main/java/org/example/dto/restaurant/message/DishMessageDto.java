package org.example.dto.restaurant.message;

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
public class DishMessageDto implements Serializable {

    @NotNull
    private UUID dishId;

    @Min(1)
    @NotNull
    private Integer quantity;

}
