package com.samgyeobsal.service;

import com.samgyeobsal.domain.review.InsertReviewDTO;
import com.samgyeobsal.domain.review.ReplyReviewVO;

public interface ReviewService {

    boolean isWritableStoreReview(String email, String orderId);
    void insertReview(InsertReviewDTO insertReviewDTO);

    void ReplyReview(ReplyReviewVO replyReviewVO);
}
