package com.neoflex.gateway_api.model.to;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String jwtToken;
    private String phone;
}
