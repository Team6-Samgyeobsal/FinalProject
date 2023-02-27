package com.samgyeobsal.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @filename OrderRequest
 * @author 최태승
 * @since 2023.02.24
 * OrderApi에 필요한 데이터
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.02.24	최태승		최초 생성
 * </pre>
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String oId; // 주문 ID
    private int oCount; // 주문 상품 수량
    private String oConsumer; // 소비자
    private String oPhone; // 주문자 전화번호
    private String oMemo; // 주문 메모
    private String oMail; // 이메일
    private String cPid; // 쿠폰 아이디
    private int oOriginPrice; // 원래 가격
    private int oAfterPrice; // 이후 가격
    private int mMileage; // 마일리지 (hPoint)
    private int pmMethod; // 결제방식
    private String pmCompany; // 지불회사
    private String mId; // 회원 아이디
}
