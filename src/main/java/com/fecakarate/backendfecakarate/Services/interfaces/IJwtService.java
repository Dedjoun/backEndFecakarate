package com.fecakarate.backendfecakarate.Services.interfaces;


import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {

    String generateToken(UserDetails userDetails);
    String extractUsername(String token);

    Claims extractAllClaims(String token);

    boolean isTokenExpired(String token);

    Boolean isTokenValid(String token, UserDetails userDetails);
}
