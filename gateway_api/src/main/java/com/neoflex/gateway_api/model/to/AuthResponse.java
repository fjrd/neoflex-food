package com.neoflex.gateway_api.model.to;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private UUID id;
    private String name;
    private String phone;
    private String jwtToken;


}
