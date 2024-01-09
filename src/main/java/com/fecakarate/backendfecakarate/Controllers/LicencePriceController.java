package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Dtos.Licences.LicencePricedto;
import com.fecakarate.backendfecakarate.Services.interfaces.ILicencePriceService;
import com.google.zxing.WriterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping(path = "/member/management/price")
public class LicencePriceController {

    private final ILicencePriceService iLicencePriceService;

    public LicencePriceController(ILicencePriceService iLicencePriceService) {
        this.iLicencePriceService = iLicencePriceService;
    }


    @PostMapping(path = "V1/add")
    public ResponseEntity<?> add(@RequestBody LicencePricedto licencePricedto) throws IOException, WriterException {
        return ResponseEntity.ok(iLicencePriceService.add(licencePricedto));
    }

    @PutMapping(path = "V1/update")
    public ResponseEntity<?> update(@RequestParam(name = "id") Long id,@RequestBody LicencePricedto licencePricedto){
        return ResponseEntity.ok(iLicencePriceService.update(id,licencePricedto));
    }

    @GetMapping(path = "V1/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(iLicencePriceService.getAll());
    }

    @DeleteMapping(path = "V1/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(iLicencePriceService.delete(id));
    }
}
