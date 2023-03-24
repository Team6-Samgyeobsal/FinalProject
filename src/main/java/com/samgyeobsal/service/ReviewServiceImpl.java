package com.samgyeobsal.service;

import com.samgyeobsal.domain.review.InsertReviewDTO;
import com.samgyeobsal.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewMapper reviewMapper;
    private final CommonService commonService;

    @Override
    public boolean isWritableStoreReview(String email, String orderId) {
        return reviewMapper.isWritableStoreReview(email, orderId);
    }

    @Override
    public void insertReview(InsertReviewDTO insertReviewDTO) {
        int score = commonService.getReviewScore(insertReviewDTO.getRcontent());
        insertReviewDTO.setRscore(score);
        int row = reviewMapper.insertReview(insertReviewDTO);
        if(row == 0) throw new RuntimeException("review Mapper Error");
    }
}