package com.samgyeobsal.domain.coupon;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Schema(description = "쿠폰 발급 객체")
public class IssueCouponVO {

    @Schema(title = "쿠폰 아이디")
    private String cpid;
    @Schema(title = "이메일")
    private String memail;
    @Schema(title = "쿠폰 유효기간")
    private Date cpexpiredate;
    @Schema(title = "이벤트아이디")
    private String eid;
}
