package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Services.interfaces.IListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/public")
public class ListController {

    private final IListService listService;
    @GetMapping(path = "/listFonction")
    public ResponseEntity<?> getFonction(){
        return ResponseEntity.ok(listService.getFonctionSystems());
    }
    @GetMapping(path = "/listStatus")
    public ResponseEntity<?> getStatus(){
        return ResponseEntity.ok(listService.getStatuSystems());
    }
    @GetMapping(path = "/listRegion")
    public ResponseEntity<?> getRegion(){
        return ResponseEntity.ok(listService.getRegion());
    }
    @GetMapping(path = "/listDepartement")
    public ResponseEntity<?> getDepartement(){
        return ResponseEntity.ok(listService.getDepartement());
    }

    @GetMapping(path = "/listGrade")
    public ResponseEntity<?> getById(){
        return ResponseEntity.ok(listService.getGrade());
    }
}
