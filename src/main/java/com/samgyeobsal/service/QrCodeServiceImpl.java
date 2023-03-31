package com.samgyeobsal.service;

import com.google.zxing.WriterException;
import com.samgyeobsal.domain.common.UploadImgDTO;
import com.samgyeobsal.util.QRCodeGenerator;
import com.samgyeobsal.mapper.QrCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

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
// 해당 클래스를 스프링의 Bean으로 등록해주는 애너테이션. Component Scan 기능을 이용해 스프링 컨테이너가 자동으로 생성
@Service
// Field Injection 대신 생성자를 이용해 의존성 주입
@RequiredArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {
    private final QrCodeMapper qrCodeMapper; // QrCodeMapper 빈 객체 주입을 위한 final 필드
    private final ImageUploadService imageUploadService;

    // 매개변수로 받은 문자열 링크를 이용해 QR 코드를 생성하고 DB에 삽입한 후, 생성된 QR 코드 바이트 배열을 반환하는 메서드
    public void generateQrCode(String oid) throws IOException, WriterException {

        // QR 코드 생성
        MultipartFile multipartFile = QRCodeGenerator.generateQRCodeImage(oid);

        UploadImgDTO uploadImgDTO = imageUploadService.uploadImg(multipartFile);
        String qid = UUID.randomUUID().toString();

        // DB에 QR 코드 이미지 삽입
        qrCodeMapper.insertQrCode(qid, oid, uploadImgDTO.getImageURL());
    }
}
