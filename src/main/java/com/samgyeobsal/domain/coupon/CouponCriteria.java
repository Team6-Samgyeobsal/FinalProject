package com.samgyeobsal.domain.coupon;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponCriteria {

    private String memail;
    private String used;
    private int page;
}
