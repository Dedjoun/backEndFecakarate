package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationRequest;
import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Exceptions.OrganizationException;
import com.fecakarate.backendfecakarate.Services.interfaces.IAuthenticationService;
import com.fecakarate.backendfecakarate.Services.interfaces.IOrganizationService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/V1/auth")
@Slf4j
public class AuthenticationController {

    private final IAuthenticationService iAuthenticationService;

    private final IOrganizationService organizationService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(iAuthenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/registryOrg")
    public ResponseEntity<?> registryOrg(@RequestBody OrganizationDto organizationDto) throws IOException, WriterException, OrganizationException {
        return ResponseEntity.ok(organizationService.add(organizationDto));
    }
}
