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

    /**
     * 로그인한 계정의 사용한 쿠폰과 사용안한 쿠폰 리스트를 리턴
     * @param couponCriteria
     */
    @Override
    public List<CouponVO> getCouponList(CouponCriteria couponCriteria) {

        return couponMapper.getCouponList(couponCriteria);
    }

    /**
     * 로그인한 계정의 사용 안한 쿠폰 수를 리턴
     * @param memail 회원 이메일
     */
    @Override
    public int couponCount(String memail) {
        return couponMapper.couponCount(memail);
    }

    /**
     * 로그인한 계정으로 쿠폰 발급
     * @param issueCouponVO
     */
    @Override
    public void issueCoupon(IssueCouponVO issueCouponVO){

        String cpid=UUID.randomUUID().toString();
        issueCouponVO.setCpid(cpid);
        couponMapper.issueCoupon(issueCouponVO);
    }
}
