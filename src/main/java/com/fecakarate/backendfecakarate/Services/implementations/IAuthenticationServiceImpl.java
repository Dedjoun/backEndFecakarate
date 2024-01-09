package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Config.JwtService;
import com.fecakarate.backendfecakarate.Dtos.auth.RegisterRequest;
import com.fecakarate.backendfecakarate.Enums.Role;
import com.fecakarate.backendfecakarate.Models.CustomUserDetails;
import com.fecakarate.backendfecakarate.Models.Users;
import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationRequest;
import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationResponse;
import com.fecakarate.backendfecakarate.Repository.UserRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class IAuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Value("${user.oauth.accessTokenValidity}")
    private int accessTokenValidity;

    @Value("${user.oauth.refreshTokenValidity}")
    private int refreshTokenValidity;

    @Value("${user.oauth.scope}")
    private String scope;

    @Value("${user.oauth.grant_type}")
    private String grantType;

    @Value("${user.oauth.token_type}")
    private String tokenType;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        try {
            try{
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationRequest.getEmail(),
                                authenticationRequest.getPassword()
                        )
                );
            }catch (Exception e){
                log.warn("Error:"+e);
                log.info("Error:"+authenticationRequest);
                throw new RuntimeException(e);
            }

            Users user = userRepo.findByEmail(authenticationRequest.getEmail()).orElseThrow();
            log.info("MY USER ==> "+ user);
            String jwtToken = jwtService.generateTokenWithUserDetails(new CustomUserDetails(user));
            log.info("MY TOKEN ==> "+ jwtToken);
            return AuthenticationResponse.builder()
                    .access_token(jwtToken)
                    .token_type(tokenType)
                    .expires_in(accessTokenValidity)
                    .scope(scope)
                    .role(user.getRole().name())
                    .build();
        }catch (Exception e){
            throw new  RuntimeException(e);
        }

    }

    public AuthenticationResponse register(RegisterRequest request) {
        Users user = new Users();
        user.setFirstname(request.getFirstname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        if (request.getRole() == null){
            user.setRole(Role.Admin);
        }else {
            user.setRole(Role.User);
        }

        userRepo.save(user);
        String jwtToken = jwtService.generateTokenWithUserDetails(new CustomUserDetails(user));
        return AuthenticationResponse.builder()
                .access_token(jwtToken)
                .token_type(tokenType)
                .expires_in(accessTokenValidity)
                .scope(scope)
                .role(user.getRole().name())
                .build();
    }

}
