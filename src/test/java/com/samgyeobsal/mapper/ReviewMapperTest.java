package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.ReviewCriteria;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.domain.review.InsertReviewDTO;
import com.samgyeobsal.domain.review.ReplyReviewVO;
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
        reviewCriteria.setType("funding");
        List<ReviewVO> list = fundingMapper.getReviewList(reviewCriteria);
        list.forEach(review -> log.info("review = {}",review));
        assertThat(list.get(0).getRcontent().equals("그냥 괜찮아요~"));
    }

    @Transactional
    @Test
    void ReplyReviewTest(){
        ReplyReviewVO vo = new ReplyReviewVO();
        vo.setFid("1");
        vo.setMemail("user@gmail.com");
        vo.setRtype("FUNDING");
        vo.setRecontent("감사합니다");
        reviewMapper.replyReview(vo);

        ReviewCriteria reviewCriteria = new ReviewCriteria();
        reviewCriteria.setFid("1");
        reviewCriteria.setSort("1");
        reviewCriteria.setType("funding");
        List<ReviewVO> list = fundingMapper.getReviewList(reviewCriteria);
        list.forEach(review -> log.info("review = {}",review));
    }
}
