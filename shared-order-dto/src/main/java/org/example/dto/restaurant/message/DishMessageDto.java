package org.example.dto.restaurant.message;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;


public class DishMessageDto implements Serializable {

    @NotNull
    private UUID dish_id;

    @Min(1)
    @NotNull
    private Integer quantity;

}
