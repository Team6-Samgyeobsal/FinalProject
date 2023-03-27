package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.coupon.CouponCriteria;
import com.samgyeobsal.domain.coupon.CouponVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class CouponMapperTest {
    @Autowired
    CouponMapper couponMapper;

    @Test
    public void getCouponListTest(){
        CouponCriteria couponCriteria = new CouponCriteria();
        couponCriteria.setMemail("user10@gmail.com");
        couponCriteria.setPage(0);
        couponCriteria.setUsed("0");
        List<CouponVO> list=couponMapper.getCouponList(couponCriteria);
        list.forEach(coupon -> log.info(coupon));
    }
}
