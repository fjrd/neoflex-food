package com.neoflex.deliveryservice.model.to;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class CourierDto {
    @NotNull
    @Length(min = 4, max = 25)
    private String name;
}
