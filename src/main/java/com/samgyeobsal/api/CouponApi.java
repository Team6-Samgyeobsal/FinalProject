package com.samgyeobsal.api;

import com.samgyeobsal.domain.coupon.CouponCriteria;
import com.samgyeobsal.domain.coupon.CouponVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.CouponService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/coupon")
public class CouponApi {

    @Autowired
    CouponService couponService;
    @GetMapping("/mypage")
    public ResponseEntity<List<CouponVO>> getCouponList(@AuthenticationPrincipal Account account, CouponCriteria couponCriteria){
        couponCriteria.setMemail(account.getMember().getMemail());
        log.info("couponList = "+couponCriteria);
        List<CouponVO> coupon= couponService.getCouponList(couponCriteria);
        log.info("couponList111 = "+coupon);
        return new ResponseEntity<>(coupon, HttpStatus.OK);
    }
}
