package com.samgyeobsal.domain.event;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @filename Event
 * @author 최태승
 * @since 2023.03.17
 * Event 객체를 구성하는 필드를 정의
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.17	최태승		최초 생성
 * </pre>
 */

public class Event {
    private Integer eNo; // 이벤트 고유 번호
    private String eTitle; // 이벤트 제목
    private String eContent; // 이벤트 내용
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eIssuedate; // 이벤트 시작일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eExpireDate; // 이벤트 종료일
    private Integer eLimitCount; // 이벤트 수 제한
    private Integer eCount; // 이벤트 수
    private String eImg; // 이벤트 대표 이미지
    private Integer eDiscount; // 할인율
    private Integer eStatus; // 이벤트 상태
    private String eDetailImg; // 상세 내용 이미지
    private String eCouponTitle; // 쿠폰 이름
    private String cPid; // 쿠폰 번호
}
