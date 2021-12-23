package com.neoflex.customer_service.model.to;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerDto {
    private UUID id;
    private String name;
    private String phone;
}
