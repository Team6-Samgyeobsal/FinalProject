package com.samgyeobsal.service;

import com.samgyeobsal.domain.coupon.CouponCriteria;
import com.samgyeobsal.domain.coupon.CouponVO;
import com.samgyeobsal.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{
    @Autowired
    CouponMapper couponMapper;
    @Override
    public List<CouponVO> getCouponList(CouponCriteria couponCriteria) {

        return couponMapper.getCouponList(couponCriteria);
    }
}
