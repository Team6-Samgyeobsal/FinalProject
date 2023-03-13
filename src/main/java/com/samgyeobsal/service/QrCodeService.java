package com.samgyeobsal.service;

import com.google.zxing.WriterException;

import java.io.IOException;
/**
 * @filename QrCodeSerivce
 * @author 최태승
 * @since 2023.03.06
 * Qr코드 비즈니스 로직 처리, 데이터베이스와 상호작용
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.06	최태승		최초 생성
 * </pre>
 */
public interface QrCodeService {
    public byte[] generateQrCode(String link) throws IOException, WriterException;

}
