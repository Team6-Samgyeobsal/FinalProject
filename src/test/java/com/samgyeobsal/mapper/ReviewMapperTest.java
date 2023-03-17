package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.ReviewCriteria;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.domain.review.InsertReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
public class ReviewMapperTest {

    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private FundingMapper fundingMapper;

    @Test
    void isWritableStoreReview() {
        String email = "user@gmail.com";
        String oid = "1";
        Boolean writable = reviewMapper.isWritableStoreReview(email, oid);
        log.info("writable = {}", writable);
    }

    @Transactional
    @Test
    void insertFundingReviewTest(){
        InsertReviewDTO dto=new InsertReviewDTO();
        dto.setFid("1");
        dto.setMemail("user10@gmail.com");
        dto.setRcontent("그냥 괜찮아요~");
        reviewMapper.insertReview(dto);
        FundingCriteria criteria= new FundingCriteria();

        ReviewCriteria reviewCriteria = new ReviewCriteria();
        reviewCriteria.setFid("1");
        reviewCriteria.setSort("1");
        reviewCriteria.setType("1");
        List<ReviewVO> list = fundingMapper.getReviewList(reviewCriteria);
        list.forEach(review -> log.info("review = {}",review));
        assertThat(list.get(0).getRcontent().equals("그냥 괜찮아요~"));
    }
}
