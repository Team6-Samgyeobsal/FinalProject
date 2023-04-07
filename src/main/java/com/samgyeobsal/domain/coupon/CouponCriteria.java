package com.samgyeobsal.domain.coupon;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "쿠폰 페이징 객체")
public class CouponCriteria {

    @Schema(title = "유저 이메일")
    private String memail;
    @Schema(title = "사용 가능",description = "1 : 사용불가, 0: 사용가능")
    private String used;
    @Schema(title = "쿠폰 페이지")
    private int page;
}
