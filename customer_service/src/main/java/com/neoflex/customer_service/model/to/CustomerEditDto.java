package com.neoflex.customer_service.model.to;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerEditDto {
    @Length(min=3,max=30)
    private String name;
    @Length(min = 8, max = 12)
    private String phone;
    @Length(min=3,max=15)
    private String password;
}
