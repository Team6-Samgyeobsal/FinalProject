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

@Service
@RequiredArgsConstructor
public class QrCodeServiceImpl implements QrCodeService {
    private final QrCodeMapper qrCodeMapper;
    private final ImageUploadService imageUploadService;

    /**
     * 주문 아이디가 포함된 QR 코드 이미지 업로드 후 저장
     * @param oid
     */
    public void generateQrCode(String oid) throws IOException, WriterException {

        // QR 코드 생성
        MultipartFile multipartFile = QRCodeGenerator.generateQRCodeImage(oid);

        UploadImgDTO uploadImgDTO = imageUploadService.uploadImg(multipartFile);
        String qid = UUID.randomUUID().toString();

        qrCodeMapper.insertQrCode(qid, oid, uploadImgDTO.getImageURL());
    }
}
