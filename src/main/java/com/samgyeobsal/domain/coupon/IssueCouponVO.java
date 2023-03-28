package com.samgyeobsal.domain.coupon;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class IssueCouponVO {

    private String cpid;
    private String memail;
    private Date cpexpiredate;
    private String eid;
}
