package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.organization.MemberDto;
import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.Grade;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Services.interfaces.IMemberServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/member/management")
@RequiredArgsConstructor
public class MemberController {

    private final IMemberServices IMemberServices;

    @PostMapping(path = "V1/add")
    public ResponseEntity<?> add(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(IMemberServices.add(memberDto));
    }


    @PutMapping(path = "V1/update")
    public ResponseEntity<?> update(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(IMemberServices.update(memberDto));
    }

    @GetMapping(path = "V1/getById")
    public ResponseEntity<?> getById(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(IMemberServices.getById(id));
    }

    @GetMapping(path = "V1/getAll")
    public ResponseEntity<?> getAll(Pageable pageable,
                                    @RequestParam(name = "matricule", required = false) String matricule,
                                    @RequestParam(name = "fonction" , required = false) Fonction fonction,
                                    @RequestParam(name = "printStatus" , required = false) STATUS printStatus,
                                    @RequestParam(name = "grade" , required = false) Grade grade,
                                    @RequestParam(name = "organisationId" , required = false) Long organisationId,
                                    @RequestParam(name = "licenceStatus" , required = false) STATUS licenceStatus,
                                    @RequestParam(name = "from" , required = false) String from,
                                    @RequestParam(name = "to" , required = false) String to){
        return ResponseEntity.ok(IMemberServices.getALL(pageable,matricule,fonction,printStatus,grade,organisationId,licenceStatus,from,to));
    }

    @DeleteMapping(path = "V1/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(IMemberServices.delete(id));
    }

}
