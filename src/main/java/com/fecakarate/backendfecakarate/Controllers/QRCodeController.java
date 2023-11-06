package com.fecakarate.backendfecakarate.Controllers;

import com.fecakarate.backendfecakarate.Services.interfaces.IQRCodeService;
import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final IQRCodeService IQRCodeService;

    public QRCodeController(IQRCodeService IQRCodeService) {
        this.IQRCodeService = IQRCodeService;
    }

    @GetMapping
    public String generateQRCode() throws WriterException, IOException {
        return IQRCodeService.generateQRCode("Ceci est un QR code", "Teste");
    }

}
