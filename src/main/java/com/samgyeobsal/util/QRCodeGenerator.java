package com.samgyeobsal.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 주어진 링크를 인코딩하여 QR 코드 이미지를 생성하고,
     * 그 이미지를 byte 배열 형태로 반환하는 메서드
     * @param link
     * @return QR 코드 이미지를 바이트 배열 형태로 변환
     * @throws WriterException
     * @throws IOException
     */
    public static MultipartFile generateQRCodeImage(String link) throws WriterException, IOException {

        // QR코드 생성 옵션 설정
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.MARGIN, 0);
        hintMap.put(EncodeHintType.CHARACTER_SET,"UTF-8");

        // QR 코드 생성
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(link, BarcodeFormat.QR_CODE, 200, 200, hintMap);

        // QR 코드 이미지 파일 데이터 생성
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig config = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, config);
        byte[] pngData = pngOutputStream.toByteArray();

        String fileName = UUID.randomUUID().toString() + ".png";
        InputStream inputStream = new ByteArrayInputStream(pngData);
        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, "image/png", inputStream);


        return multipartFile;
    }
}