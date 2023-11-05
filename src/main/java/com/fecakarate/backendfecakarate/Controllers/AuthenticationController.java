package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationRequest;
import com.fecakarate.backendfecakarate.Services.interfaces.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")

public class AuthenticationController {

    private final IAuthenticationService iAuthenticationService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(iAuthenticationService.authenticate(authenticationRequest));
    }
}
