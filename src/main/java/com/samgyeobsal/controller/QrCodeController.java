package com.samgyeobsal.controller;

import com.google.zxing.WriterException;
import com.samgyeobsal.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.util.Base64;

/**
 * @filename QrCodeController
 * @author 최태승
 * @since 2023.03.06
 * open Qrcode
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.06	최태승		최초 생성
 * </pre>
 */

@Controller
@RequiredArgsConstructor
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @GetMapping("/qr")
    public String showQrCode(Model model) throws IOException, WriterException {

        // QR 코드 생성 및 모델에 추가
        String link = "https://www.naver.com";
        byte[] qrCodeBytes = qrCodeService.generateQrCode(link);
        String qrCode = Base64.getEncoder().encodeToString(qrCodeBytes);
        model.addAttribute("qrCode", qrCode);

        return "qr/qrcode";
    }
}
