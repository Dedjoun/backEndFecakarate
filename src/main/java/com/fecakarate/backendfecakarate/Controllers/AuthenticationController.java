package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationRequest;
import com.fecakarate.backendfecakarate.Dtos.auth.AuthenticationResponse;
import com.fecakarate.backendfecakarate.Dtos.auth.RegisterRequest;
import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Exceptions.OrganizationException;
import com.fecakarate.backendfecakarate.Models.Organization;
import com.fecakarate.backendfecakarate.Services.interfaces.IAuthenticationService;
import com.fecakarate.backendfecakarate.Services.interfaces.IOrganizationService;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api-public/auth")
@Slf4j
public class AuthenticationController {

    private final IAuthenticationService iAuthenticationService;

    private final IOrganizationService organizationService;

    public AuthenticationController(IAuthenticationService iAuthenticationService, IOrganizationService organizationService) {
        this.iAuthenticationService = iAuthenticationService;
        this.organizationService = organizationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        log.info("Log 1");
        return ResponseEntity.ok(iAuthenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(iAuthenticationService.register(request));
    }
    @PostMapping("/registryOrg")
    public ResponseEntity<Organization> registryOrg(@RequestBody OrganizationDto organizationDto) throws IOException, WriterException, OrganizationException {
        return ResponseEntity.ok(organizationService.addOrg(organizationDto));
    }


}
