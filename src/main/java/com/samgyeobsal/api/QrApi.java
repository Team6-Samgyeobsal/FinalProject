package com.samgyeobsal.api;

import com.google.zxing.WriterException;
import com.samgyeobsal.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

/**
 * @filename QrApi
 * @author 최태승
 * @since 2023.03.06
 * Qr RestController
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.06	최태승		최초 생성
 * </pre>
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/qr")
public class QrApi {

    private final QrCodeService qrCodeService;

    @PostMapping("/qrcode")
    public ResponseEntity<byte[]> generateQrCode(@RequestBody String link) throws WriterException {
        try{
            byte[] qrCodeBytes = qrCodeService.generateQrCode(link);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCodeBytes, headers, HttpStatus.OK);
        } catch(IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
