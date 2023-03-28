package com.samgyeobsal.api;

import com.samgyeobsal.domain.coupon.CouponCriteria;
import com.samgyeobsal.domain.coupon.CouponVO;
import com.samgyeobsal.domain.coupon.IssueCouponVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.CouponService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/coupon")
public class CouponApi {

    @Autowired
    CouponService couponService;
    @GetMapping("/mypage")
    public ResponseEntity<Map<String, Object>> getCouponList(@AuthenticationPrincipal Account account, CouponCriteria couponCriteria){
        couponCriteria.setMemail(account.getMember().getMemail());
        List<CouponVO> coupon= couponService.getCouponList(couponCriteria);
        Map<String, Object> res = new HashMap<>();
        res.put("couponList", coupon);
        res.put("couponCount", couponService.couponCount(account.getMember().getMemail()));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/issue")
    public ResponseEntity<String> issueCoupon(@AuthenticationPrincipal Account account, @RequestBody IssueCouponVO issueCouponVO){
        log.info("issueCouponVO"+issueCouponVO);
        issueCouponVO.setMemail(account.getMember().getMemail());
        couponService.issueCoupon(issueCouponVO);

        return new ResponseEntity<>("success",HttpStatus.OK);
    }
}
