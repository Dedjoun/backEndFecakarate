package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Services.interfaces.IMemberServices;
import com.fecakarate.backendfecakarate.Services.interfaces.IOrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-public")
public class ListDataController {
    private final IMemberServices memberServices;
    private final IOrganizationService organizationService;

    public ListDataController(IMemberServices memberServices, IOrganizationService organizationService) {
        this.memberServices = memberServices;
        this.organizationService = organizationService;
    }

    @GetMapping(path = "/listMember")
    public ResponseEntity<?> getAllPublicMembres(){
        return ResponseEntity.ok(memberServices.getAll());
    }

    @GetMapping(path = "/listOrg")
    public ResponseEntity<?> getAllPublicOrgs(){
        return ResponseEntity.ok(organizationService.getAll());
    }
}
