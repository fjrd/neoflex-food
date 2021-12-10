package com.neoflex.gateway_api.model.to;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerAuthDto {
    @NotNull
    private String phone;
    @NotNull
    private String password;
}
