package com.samgyeobsal.mapper;

import com.jayway.jsonpath.Criteria;
import com.samgyeobsal.domain.funding.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class FundingMapperTest {

    private final String fid="1";
    @Autowired
    FundingMapper fundingMapper;

    @Test
    public void getFundingListTest() {
        FundingCriteria criteria= new FundingCriteria();
        criteria.setPlace("1");
        criteria.setSort("1");
        criteria.setType("1");
        List<FundingVO> list = fundingMapper.getFundingList(criteria);
        list.forEach(funding -> log.info(funding));
    }
    @Test
    public void getFundingDetailTest(){
        FundingDetailVO fundingDetailVO = fundingMapper.getFundingDetail(fid,"FUNDING");
        System.out.println(fundingDetailVO);

    }
    @Test
    public void getReviewListTest(){
        ReviewCriteria reviewCriteria = new ReviewCriteria();
        reviewCriteria.setFid("1");
//        reviewCriteria.setSort("1");
//        reviewCriteria.setType("1");
        List<ReviewVO> list = fundingMapper.getReviewList(reviewCriteria);
        list.forEach(review -> log.info(review));
    }

    @Test
    void findProductListByFundingId(){
        String fundingId = "1";
        List<ProductVO> products = fundingMapper.findProductListByFundingId(fundingId);
        log.info("products = {}", products);
    }

    @Test
    void findProductByFundingIdAndProductId(){
        String fundingId = "1";
        String productId = "1";
        ProductVO product = fundingMapper.findProductByFundingIdAndProductId(fundingId, productId);
        log.info("product = {}", product);
        Assertions.assertEquals(product.getFpid(), productId);

    }
}
