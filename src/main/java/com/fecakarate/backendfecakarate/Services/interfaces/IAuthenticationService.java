package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Models.auth.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {
    ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest);
}
