package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Services.interfaces.IOrganisationService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/organisation/management")
public class OrganizationController {

    private final IOrganisationService IOrganisationService;

    public OrganizationController(IOrganisationService IOrganisationService) {
        this.IOrganisationService = IOrganisationService;
    }

    @PostMapping(path = "V1/add")
    public ResponseEntity<?> add(@RequestBody OrganizationDto organizationDto){
        return ResponseEntity.ok(IOrganisationService.add(organizationDto));
    }

    @PutMapping(path = "V1/update")
    public ResponseEntity<?> update(@RequestBody OrganizationDto organizationDto){
        return ResponseEntity.ok(IOrganisationService.update(organizationDto));
    }

    @GetMapping(path = "V1/getById")
    public ResponseEntity<?> getById(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(IOrganisationService.getById(id));
    }


    @GetMapping(path = "V1/getAll")
    public ResponseEntity<?> getAll(Pageable pageable,
                                    @RequestParam(name="nom", required = false) String nom,
                                    @RequestParam(name="ville", required = false) String ville,
                                    @RequestParam(name="region", required = false) String region,
                                    @RequestParam(name="departement", required = false) String departement,
                                    @RequestParam(name="quartier", required = false) String quartier,
                                    @RequestParam(name="etat", required = false) String etat,
                                    @RequestParam(name="printStatus", required = false) String printStatus){
        return ResponseEntity.ok(IOrganisationService.getALL(pageable,nom,ville,region,departement,quartier,etat,printStatus));
    }

    @DeleteMapping("V1/delete")
    public ResponseEntity<?> delete(@RequestParam(name="id") Long id){
        return ResponseEntity.ok(IOrganisationService.delete(id));
    }

}