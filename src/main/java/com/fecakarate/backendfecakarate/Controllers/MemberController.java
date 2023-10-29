package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.MemberDto;
import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.GRADE;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Services.interfaces.MemberServices;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/member/management")
public class MemberController {

    private final MemberServices memberServices;

    public MemberController(MemberServices memberServices) {
        this.memberServices = memberServices;
    }


    @PostMapping(path = "V1/add")
    public ResponseEntity<?> add(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberServices.add(memberDto));
    }


    @PutMapping(path = "V1/update")
    public ResponseEntity<?> update(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberServices.update(memberDto));
    }

    @GetMapping(path = "V1/getById")
    public ResponseEntity<?> getById(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(memberServices.getById(id));
    }

    @GetMapping(path = "V1/getAll")
    public ResponseEntity<?> getAll(Pageable pageable,
                                    @RequestParam(name = "matricule", required = false) String matricule,
                                    @RequestParam(name = "fonction" , required = false) Fonction fonction,
                                    @RequestParam(name = "printStatus" , required = false) STATUS printStatus,
                                    @RequestParam(name = "grade" , required = false) GRADE grade,
                                    @RequestParam(name = "organisationId" , required = false) String organisationId,
                                    @RequestParam(name = "licenceStatus" , required = false) STATUS licenceStatus){
        return ResponseEntity.ok(memberServices.getALL(pageable,matricule,fonction,printStatus,grade,organisationId,licenceStatus));
    }

    @DeleteMapping(path = "V1/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(memberServices.delete(id));
    }

}
