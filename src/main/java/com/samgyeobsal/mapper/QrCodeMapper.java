package com.samgyeobsal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * @filename QrCodeMapper
 * @author 최태승
 * @since 2023.03.06
 * Mybatis 매핑 XML에 기재된 SQL을 호출하기 위한 인터페이스
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.06	최태승		최초 생성
 * </pre>
 */
@Mapper
public interface QrCodeMapper {
    /**
     * 아래의 매개변수를 DB에 QR코드 정보로 삽입하는 역할
     *
     * @param qrCode
     */
    void insertQrCode(@Param("qid") String qid, @Param("oid") String oid,
                      @Param("qrCode") byte[] qrCode);
}
