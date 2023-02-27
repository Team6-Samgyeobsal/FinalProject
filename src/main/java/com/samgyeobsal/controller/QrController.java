package com.samgyeobsal.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("qr")
public class QrController {

    public String createQR() throws Exception{
        BitMatrix bitMatrix = null;
        MatrixToImageConfig matrixToImageConfig = null;
        //QRcode에 담고 싶은 정보를 문자열로 표시(URL)
        String codeInfo = "";

        // QRcode 바코드와 배경 색상
        int onColor = 0xFF2e4e96; // 바코드 색
        int offColor = 0xFFFFFFFF; // 배경 색

        //QRCode 만들 때 사용하는 클래스
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        matrixToImageConfig = new MatrixToImageConfig();
        Map<EncodeHintType, String> hints = new HashMap<>();
        //Generics.newHashMap();
        hints.put(EncodeHintType.ERROR_CORRECTION,"L");

        //QRCode 크기(fixel)
        int width = 1000;
        int height = 1000;

        try{
            //bitMatrix 형식으로 QRCode 생성
            bitMatrix = qrCodeWriter.encode(codeInfo, BarcodeFormat.QR_CODE,width, height);

        } catch (Exception e){
            e.printStackTrace();
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, matrixToImageConfig);

        // QRCode 저장
        String savePath = "";
        String saveFileName = "qrImage.png";
        File file = new File(savePath);
        if(!file.exists()){
            file.mkdirs();
        }

        BufferedImage bufferedImage = null;
        bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
        File saveFile = new File(savePath+saveFileName);
        ImageIO.write(bufferedImage,"png",saveFile);

        // byteArray를 base64로 변환하는 이유 : 프론트에서 파일경로가 아닌 binary 형식으로 전송해서 보여주기 위함
        // 이렇게 할 경우 DB에 이미지를 저장하지 않고 화면에 보여줄 수 있음
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());


    }
}
