package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Services.interfaces.IOrganizationService;
import com.google.zxing.WriterException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(path = "/organisation/management")
public class OrganizationController {

    private final IOrganizationService organizationService;

    public OrganizationController(IOrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping(path = "V1/addOrg")
    public ResponseEntity<?> addOrg(@RequestBody OrganizationDto organizationDto) throws IOException, WriterException {
        return ResponseEntity.ok(organizationService.addOrg(organizationDto));
    }

    @PutMapping(path = "V1/updateOrg")
    public ResponseEntity<?> updateOrg(@RequestBody OrganizationDto organizationDto){
        return ResponseEntity.ok(organizationService.updateOrg(organizationDto));
    }

    @GetMapping(path = "V1/getByIdOrg")
    public ResponseEntity<?> getByIdOrg(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(organizationService.getByIdOrg(id));
    }


    @GetMapping(path = "V1/getAllOrg")
    public ResponseEntity<?> getAllOrg(Pageable pageable,
                                    @RequestParam(name="nom", required = false) String nom,
                                    @RequestParam(name="ville", required = false) String ville,
                                    @RequestParam(name="region", required = false) String region,
                                    @RequestParam(name="departement", required = false) String departement,
                                    @RequestParam(name="quartier", required = false) String quartier,
                                    @RequestParam(name="etat", required = false) String etat,
                                    @RequestParam(name="printStatus", required = false) String printStatus,
                                    @RequestParam(name = "from" , required = false) String from,
                                    @RequestParam(name = "to" , required = false) String to){
        return ResponseEntity.ok(organizationService.getALLOrg(pageable,nom,ville,region,departement,quartier,etat,printStatus,from,to));
    }

    @DeleteMapping("V1/deleteOrg")
    public ResponseEntity<?> deleteOrg(@RequestParam(name="id") Long id){
        return ResponseEntity.ok(organizationService.deleteOrg(id));
    }

}
