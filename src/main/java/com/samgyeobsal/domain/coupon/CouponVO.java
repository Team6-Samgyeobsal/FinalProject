package com.samgyeobsal.domain.coupon;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CouponVO {
    private String eid;
    private String cpid;
    private String etitle;
    private int ediscount;
    private String etype;
    private Date cpissuedate;
    private Date cpexpiredate;
    private Date cpusedate;




}
