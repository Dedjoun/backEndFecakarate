package com.fecakarate.backendfecakarate.Services.interfaces;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface IQRCodeService {
    String generateQRCode(String data, String userMatricule) throws WriterException, IOException;

}
