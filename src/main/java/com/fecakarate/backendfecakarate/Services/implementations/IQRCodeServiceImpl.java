package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Services.interfaces.IQRCodeService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IQRCodeServiceImpl implements IQRCodeService {

    @Override
    public String generateQRCode(String data, String userMatricule) throws WriterException, IOException {


        try {
            // Créer un objet QRCodeWriter
            QRCodeWriter writer = new QRCodeWriter();

            // Spécifier le format du QRCode
            BarcodeFormat format = BarcodeFormat.QR_CODE;

            // Spécifier le niveau d'erreur
            ErrorCorrectionLevel level = ErrorCorrectionLevel.H;

            // Créer une table de hachage pour les paramètres d'encodage
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, level);

            // Générer une matrice de bits
            BitMatrix bitMatrix = writer.encode(data, format, 200, 200, hints);

            // Créer une image à partir de la matrice de bits
            BufferedImage image = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                for (int y = 0; y < bitMatrix.getHeight(); y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            String fileName = userMatricule+".png";

            // Enregistrer l'image
            ImageIO.write(image, "png", new File(fileName));

            return "QRCODE GENERE POUR " + data;
        }catch (Exception e){
            log.warn("Error to generate QRCODE", e);
            throw  new IOException(e);
        }
    }
}
