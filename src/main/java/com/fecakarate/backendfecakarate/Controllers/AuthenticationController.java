package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Models.auth.AuthenticationRequest;
import com.fecakarate.backendfecakarate.Services.interfaces.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthenticationController {

    private final IAuthenticationService iAuthenticationService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        log.info("Login API");
        return ResponseEntity.ok(iAuthenticationService.authenticate(authenticationRequest));
    }
}
