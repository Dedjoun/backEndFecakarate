package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Models.Role;
import com.fecakarate.backendfecakarate.Models.Users;
import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationRequest;
import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationResponse;
import com.fecakarate.backendfecakarate.Repository.RoleCustomRepo;
import com.fecakarate.backendfecakarate.Repository.UserRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IAuthenticationService;
import com.fecakarate.backendfecakarate.Services.interfaces.IJwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class IAuthenticationServiceImpl implements IAuthenticationService {
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;
    private final RoleCustomRepo roleCustomRepo;


    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest){
        try {
            Users users = userRepo.findByEmail(authenticationRequest.getEmail()).orElseThrow(()->new NoSuchElementException("User Not FOUND"));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            List<Role> role = null;
            if (users!=null){
                role=roleCustomRepo.getRole(users);
            }

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            Set<Role> set = new HashSet<>();
            assert role != null;
            role.forEach(c->{set.add(new Role(c.getName()));
                authorities.add(new SimpleGrantedAuthority(c.getName()));
            });

            users.setRoles(set);
            set.forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
            var jwtAccessToken = jwtService.generateToken(users, authorities);
            var jwtRefreshToken = jwtService.generateRefreshToken(users, authorities);

            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .access_token(jwtAccessToken)
                    .refresh_token(jwtRefreshToken)
                    .email(users.getEmail())
                    .user_name(users.getName())
                    .build());
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body("Invalid Credential");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }


}
