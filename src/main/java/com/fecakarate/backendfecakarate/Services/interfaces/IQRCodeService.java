package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Enums.QrcodeDest;
import com.google.zxing.WriterException;

import java.io.IOException;

public interface IQRCodeService {
    String generateQRCode(QrcodeDest qrcodeDest, String data, String userMatricule) throws WriterException, IOException;

}
