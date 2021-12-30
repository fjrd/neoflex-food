package org.example.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class RestaurantDto {

    @NotNull
    private UUID restaurantId;

    @NotBlank
    private String name;

    @NotNull
    private List<DishDto> menu;

    @NotBlank
    private String restaurantAddress;

}
