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

    @Override
    public boolean isWritableStoreReview(String email, String orderId) {
        return reviewMapper.isWritableStoreReview(email, orderId);
    }

    @Override
    public int insertReview(InsertReviewDTO insertReviewDTO) {
        return reviewMapper.insertReview(insertReviewDTO);
    }
}
