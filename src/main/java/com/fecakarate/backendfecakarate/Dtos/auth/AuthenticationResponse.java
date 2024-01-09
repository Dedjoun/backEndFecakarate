package com.fecakarate.backendfecakarate.Dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String access_token;
    private String token_type;
    private Integer expires_in;
    private String scope;
    private String role;
}
