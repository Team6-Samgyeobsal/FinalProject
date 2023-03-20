package com.samgyeobsal.service;

import com.samgyeobsal.domain.review.InsertReviewDTO;

public interface ReviewService {

    boolean isWritableStoreReview(String email, String orderId);
    void insertReview(InsertReviewDTO insertReviewDTO);
}
