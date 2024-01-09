package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Enums.QrcodeDest;
import com.fecakarate.backendfecakarate.Services.interfaces.IQRCodeService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IQRCodeServiceImpl implements IQRCodeService {

    @Override
    public String generateQRCode(QrcodeDest qrcodeDest, String data, String userMatricule) throws IOException {


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

            // Enregistrer l'image
            Path path = null;
            if (qrcodeDest == QrcodeDest.Admin){
                path = Paths.get("src/main/resources/qrcode/Admin/", userMatricule+".png");
            } else if (qrcodeDest == QrcodeDest.Club) {
                path = Paths.get("src/main/resources/qrcode/Club/", userMatricule+".png");
            }else if (qrcodeDest == QrcodeDest.Membre){
                path = Paths.get("src/main/resources/qrcode/Membre/", userMatricule+".png");
            }
            if (path==null){
                throw  new RuntimeException("Error Path Is null");
            }
            // Enregistrer l'image au format PNG
            ImageIO.write(image, "PNG", new File(path.toString()));

            log.info("QRCODE GENERE POUR " + data);
            return "QRCODE GENERE POUR " + data;
        }catch (Exception e){
            log.warn("Error to generate QRCODE", e);
            throw  new IOException(e);
        }
    }
}
