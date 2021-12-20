package com.neoflex.gateway_api.model.to;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private long id;
    private String name;
    private String phone;
    private String jwtToken;


}
