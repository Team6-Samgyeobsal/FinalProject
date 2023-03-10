package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.order.ProductDetailVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
