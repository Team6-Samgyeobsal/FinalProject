package com.samgyeobsal.domain.qr;

import com.google.zxing.WriterException;
import com.samgyeobsal.controller.QRCodeGenerator;
import com.samgyeobsal.mapper.QrCodeMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * @filename QrCodeRepository
 * @author 최태승
 * @since 2023.03.06
 * Qr코드 데이터 저장
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.06	최태승		최초 생성
 * </pre>
 */
@Repository
public class QrCodeRepository {

    private final QrCodeMapper qrCodeMapper; // 데이터베이스와 상호작용을 위해 생성자에서 해당 객체를 받아와 필드에 할당함


    public QrCodeRepository(QrCodeMapper qrCodeMapper) {
        this.qrCodeMapper = qrCodeMapper;
    }

    public void saveQrCode(String link, Long id) throws IOException, WriterException {
        link = "https://www.naver.com";
        id = 12L;

        // QR 코드 생성
        byte[] qrCodeBytes = QRCodeGenerator.generateQRCodeImage(link);

        // 데이터베이스에 QR 코드 삽입
        qrCodeMapper.insertQrCode(link, qrCodeBytes);
    }
}
