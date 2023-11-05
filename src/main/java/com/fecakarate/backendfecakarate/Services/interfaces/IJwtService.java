package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Models.Users;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

public interface IJwtService {

    String generateToken(Users users, Collection<SimpleGrantedAuthority> authorities);

    String generateRefreshToken(Users users, Collection<SimpleGrantedAuthority> authorities);
}
