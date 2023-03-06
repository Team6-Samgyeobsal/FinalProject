package com.samgyeobsal.service;

import com.google.zxing.WriterException;
import com.samgyeobsal.controller.QRCodeGenerator;
import com.samgyeobsal.mapper.QrCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 * @filename QrCodeServiceImpl
 * @author 최태승
 * @since 2023.03.06
 * 인터페이스와 구현체를 분리
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.06	최태승		최초 생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {

    private final QrCodeMapper qrCodeMapper;

    public byte[] generateQrCode(String link) throws IOException, WriterException {

      // QR 코드 생성
      byte[] qrCodeBytes = QRCodeGenerator.generateQRCodeImage(link);

      // DB에 QR 코드 삽입
      qrCodeMapper.insertQrCode(link, qrCodeBytes);

      return qrCodeBytes;
    }
}
