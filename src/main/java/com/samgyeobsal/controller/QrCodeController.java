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
// 해당 클래스의 생성자를 자동으로 생성
// QrCodeController 생성자는 QrCodeService 타입의 매개변수를 받으며, @Autowired 어노테이션을 사용하지 않아도 자동으로 의존성 주입이 가능
@RequiredArgsConstructor
public class QrCodeController {

    // QrCodeService 클래스에 대한 객체 생성
    private final QrCodeService qrCodeService;

    /** HTTP GET 요청이 /qr 경로로 들어왔을 때 showQrCode 메서드 실행
     *
     * @param model
     * @return qr/qrcode view
     * @throws IOException
     * @throws WriterException
     */

    @GetMapping("/qr")
    public String showQrCode(Model model) throws IOException, WriterException {

        // QR 코드 생성 및 모델에 추가
        String link = "https://www.naver.com";
        // 변수에 생성된 QR 코드 이미지 데이터가 저장
        byte[] qrCodeBytes = qrCodeService.generateQrCode(link);
        // byte 배열 형태로 저장된 QR 코드 이미지 데이터를 Base64 인코딩하여 문자열 형태로 변환
        String qrCode = Base64.getEncoder().encodeToString(qrCodeBytes);
        // qrCode 변숫값을 qrCode라는 이름으로 Model 객체에 추가. Model 객체에 데이터를 추가하면 Spring MVC는 해당 데이터를 View로 전달하여 화면에 렌더링
        model.addAttribute("qrCode", qrCode);

        return "qr/qrcode";
    }
}
