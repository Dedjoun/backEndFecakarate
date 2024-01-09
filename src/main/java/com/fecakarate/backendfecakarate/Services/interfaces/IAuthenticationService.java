package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationRequest;
import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationResponse;
import com.fecakarate.backendfecakarate.Dtos.auth.RegisterRequest;

public interface IAuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    AuthenticationResponse register(RegisterRequest request);

}
