package com.fecakarate.backendfecakarate.Services.implementations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fecakarate.backendfecakarate.Models.Users;
import com.fecakarate.backendfecakarate.Repository.RoleCustomRepo;
import com.fecakarate.backendfecakarate.Repository.UserRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IJwtService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class IJwtServiceImpl implements IJwtService {


    private @Value("${secret.key}") String secretKey;


    private final UserRepo userRepo;

    private final RoleCustomRepo roleCustomRepo;

    public IJwtServiceImpl(@Value("${secret.key}")String secretKey, UserRepo userRepo, RoleCustomRepo roleCustomRepo) {
        this.secretKey = secretKey;
        this.userRepo = userRepo;
        this.roleCustomRepo = roleCustomRepo;
    }

    public String generateToken(Users users, Collection<SimpleGrantedAuthority> authorities){
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        return JWT.create()
                .withSubject(users.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+50*60*1000))
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }


    public String generateRefreshToken(Users users, Collection<SimpleGrantedAuthority> authorities){
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        return JWT.create()
                .withSubject(users.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+70*60*1000))
                .sign(algorithm);
    }
}
