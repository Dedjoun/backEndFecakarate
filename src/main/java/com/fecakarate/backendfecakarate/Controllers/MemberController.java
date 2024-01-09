package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.organization.MemberDto;
import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.Grade;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Services.interfaces.IMemberServices;
import com.google.zxing.WriterException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping(path = "/member/management")
public class MemberController {

    private final IMemberServices memberServices;

    public MemberController(IMemberServices memberServices) {
        this.memberServices = memberServices;
    }

    @PostMapping(path = "V1/add")
    public ResponseEntity<?> addMember(@RequestBody MemberDto memberDto) throws IOException, WriterException {
        return ResponseEntity.ok(memberServices.addMember(memberDto));
    }

    @PutMapping(path = "V1/updateOrg")
    public ResponseEntity<?> updateMember(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberServices.updateMember(memberDto));
    }

    @GetMapping(path = "V1/getById")
    public ResponseEntity<?> getByIdMember(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(memberServices.getByIdMember(id));
    }

    @GetMapping(path = "/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Ok");
    }


    @GetMapping(path = "V1/getAll")
    public ResponseEntity<?> getAllMember(Pageable pageable,
                                    @RequestParam(name = "matricule", required = false) String matricule,
                                    @RequestParam(name = "fonction" , required = false) Fonction fonction,
                                    @RequestParam(name = "printStatus" , required = false) STATUS printStatus,
                                    @RequestParam(name = "grade" , required = false) Grade grade,
                                    @RequestParam(name = "organisationId" , required = false) Long organisationId,
                                    @RequestParam(name = "licenceStatus" , required = false) STATUS licenceStatus,
                                    @RequestParam(name = "sexe" , required = false) String sexe,
                                    @RequestParam(name = "region" , required = false) String region,
                                    @RequestParam(name = "departement" , required = false) String departement,
                                    @RequestParam(name = "from" , required = false) String from,
                                    @RequestParam(name = "to" , required = false) String to){
        return ResponseEntity.ok(memberServices.getALLMember(pageable,matricule,fonction,printStatus,grade,organisationId,licenceStatus,sexe,region,departement,from,to));
    }

    @DeleteMapping(path = "V1/delete")
    public ResponseEntity<?> deleteMember(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(memberServices.deleteMember(id));
    }

}
