package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.OrganisationDto;
import com.fecakarate.backendfecakarate.Services.interfaces.IClubServices;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/organisation/management")
public class OrganisationController {

    private final IClubServices IClubServices;

    public OrganisationController(IClubServices IClubServices) {
        this.IClubServices = IClubServices;
    }

    @PostMapping(path = "V1/add")
    public ResponseEntity<?> add(@RequestBody OrganisationDto organisationDto){
        return ResponseEntity.ok(IClubServices.add(organisationDto));
    }

    @PutMapping(path = "V1/update")
    public ResponseEntity<?> update(@RequestBody OrganisationDto organisationDto){
        return ResponseEntity.ok(IClubServices.update(organisationDto));
    }

    @GetMapping(path = "V1/getById")
    public ResponseEntity<?> getById(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(IClubServices.getById(id));
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
        return ResponseEntity.ok(IClubServices.getALL(pageable,nom,ville,region,departement,quartier,etat,printStatus));
    }

    @DeleteMapping("V1/delete")
    public ResponseEntity<?> delete(@RequestParam(name="id") Long id){
        return ResponseEntity.ok(IClubServices.delete(id));
    }

}
