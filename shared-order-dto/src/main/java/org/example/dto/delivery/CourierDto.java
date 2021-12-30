package org.example.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CourierDto {

    @NotNull
    private UUID courierId;

    @NotBlank
    private String name;

}
