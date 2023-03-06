package com.samgyeobsal.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class TossOrder {
    private String osId; // 주문 ID
    private int osCount; // 주문 상품 수량
    private String osConsumer; // 소비자
    private String osPhone; // 주문자 전화번호
    private String osMemo; // 주문 메모
    private String osMail; // 이메일
    private String csPid; // 쿠폰 아이디
    private String osState; // 상태
    private int osOriginPrice; // 원래 가격
    private int osAfterPrice; // 이후 가격
    private int msMileage; // 마일리지 (hPoint)
    private int pmMethod; // 결제방식
    private String pmCompany; // 지불회사
    private String msId; // 회원 아이디
    private Date osDate; // 주문 날짜
}
