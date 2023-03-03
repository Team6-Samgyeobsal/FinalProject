package com.samgyeobsal.controller;

import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

public class PaymentController {

    @PostMapping("/payment")
    public String makePayment() throws IOException, WriterException {

        // 결제가 완료되면 QR 코드를 생성합니다.
        String paymentResultLink = "https://localhost:80/paymentresult";
        String qrCodeImage = QRCodeGenerator.generateQRCodeImage(paymentResultLink, 300, 300);

        // HTML 페이지에 QR 코드 이미지를 추가합니다.
        String htmlPage = "<html><head></head><body><img src=\"data:image/png;base64, " + qrCodeImage + "\"/></body></html>";

        return htmlPage;
    }
}

