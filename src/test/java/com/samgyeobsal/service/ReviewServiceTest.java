package com.samgyeobsal.service;

import com.samgyeobsal.domain.review.InsertReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    @Transactional
    void insertReview(){
        InsertReviewDTO review = new InsertReviewDTO();
        review.setRcontent("재밌어요");
        review.setFid("1");
        review.setMemail("user@gmail.com");
        review.setRtype("STORE");
        reviewService.insertReview(review);
    }
}
