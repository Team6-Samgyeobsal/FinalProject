package com.samgyeobsal.service;

import com.samgyeobsal.domain.coupon.CouponCriteria;
import com.samgyeobsal.domain.coupon.CouponVO;
import com.samgyeobsal.domain.coupon.IssueCouponVO;
import com.samgyeobsal.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CouponServiceImpl implements CouponService{
    @Autowired
    CouponMapper couponMapper;
    @Override
    public List<CouponVO> getCouponList(CouponCriteria couponCriteria) {

        return couponMapper.getCouponList(couponCriteria);
    }

    @Override
    public int couponCount(String memail) {
        return couponMapper.couponCount(memail);
    }
    @Override
    public void issueCoupon(IssueCouponVO issueCouponVO){

        String cpid=UUID.randomUUID().toString();
        issueCouponVO.setCpid(cpid);
        couponMapper.issueCoupon(issueCouponVO);
    }
}
