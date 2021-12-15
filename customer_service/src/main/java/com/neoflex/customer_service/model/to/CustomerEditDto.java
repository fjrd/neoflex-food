package com.neoflex.customer_service.model.to;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class CustomerEditDto {
    @NotNull
    @Length(min=3,max=30)
    private String name;
    @NotNull
    @Length(min = 8, max = 12)
    private String phone;
    @NotNull
    @Length(min=3,max=15)
    private String password;
}
