package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.coupon.CouponCriteria;
import com.samgyeobsal.domain.coupon.CouponVO;
import com.samgyeobsal.domain.coupon.IssueCouponVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponMapper {

    List<CouponVO> getCouponList(@Param("criteria") CouponCriteria criteria);

    int couponCount(String memail);

    int issueCoupon(IssueCouponVO issueCouponVO);
}
