package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.order.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class OrderMapperTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    public void getProductListTest(){
        List<ProductDetailVO> productDetailVO=orderMapper.getProductList("1");
        productDetailVO.forEach(productDetail -> log.info(productDetail));
    }


    @Test
    public void findOrderListByMemberId(){
        String memberId = "user@gmail.com";
        List<OrderVO> orderList = orderMapper.findOrderListByMemberId(memberId);
        log.info("orderList = {}", orderList);

    }
}
