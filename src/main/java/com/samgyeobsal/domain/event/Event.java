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
 * 2023.03.26   최태승        DB 구조 변경에 따른 리팩토링
 * </pre>
 */

public class Event {
    private Integer eId; // 이벤트 고유 번호
    private String eTitle; // 이벤트 제목
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eIssuedate; // 이벤트 시작일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eExpireDate; // 이벤트 종료일
    private String eThumb; // 이벤트 썸네일
    private Integer eDiscount; // 할인율
    private String eCouponTitle; // 쿠폰 이름
    private String eType; // 이벤트 타입
    private String eContent; // 이벤트 내용
    private String cpId; // 쿠폰 번호
    private String mEmail; // 사용자 id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cpIssueDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String cpExpireDate;

}
