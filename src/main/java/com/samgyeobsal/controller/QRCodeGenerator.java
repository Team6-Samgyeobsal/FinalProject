package com.samgyeobsal.controller;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
/**
 * @filename QrCodeGenerator
 * @author 최태승
 * @since 2023.03.06
 * generate Qrcode
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.06	최태승		최초 생성
 * </pre>
 */
public class QRCodeGenerator {

    public static byte[] generateQRCodeImage(String link) throws WriterException, IOException {

        // QR코드 생성 옵션 설정
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.MARGIN, 0);
        hintMap.put(EncodeHintType.CHARACTER_SET,"UTF-8");

        // QR 코드 생성
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(link, BarcodeFormat.QR_CODE, 200, 200, hintMap);

        // QR 코드 이미지 생성
        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // QR 코드 이미지를 바이트 배열로 변환
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage,"png", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] qrCodeBytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        return qrCodeBytes;
    }
}