package models;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeImageGenerator {
	
	public static byte [] qrCodeGenerator(String shortUrl) throws IOException, WriterException{
		// Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable hintMap = new Hashtable();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(shortUrl, BarcodeFormat.QR_CODE, 100, 100, hintMap);
        
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(matrixWidth*matrixWidth);
        
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_ARGB);
        image.createGraphics();
 
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        
        
        graphics.setComposite(AlphaComposite.Clear);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        
        graphics.setComposite(AlphaComposite.Src);
        //graphics.setBackground(TRANSPARENT_); //Color.WHITE);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(new Color(92, 184, 17, 255));
 
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        
        ImageIO.write(image, "png", baos);
        byte [] byteArray = baos.toByteArray();
        
        baos.flush();
        
        return byteArray;
        
	}

}
