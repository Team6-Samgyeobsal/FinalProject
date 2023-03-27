package com.samgyeobsal.service;

import com.samgyeobsal.domain.coupon.CouponCriteria;
import com.samgyeobsal.domain.coupon.CouponVO;

import java.util.List;

public interface CouponService {

    List<CouponVO> getCouponList(CouponCriteria couponCriteria);


}
