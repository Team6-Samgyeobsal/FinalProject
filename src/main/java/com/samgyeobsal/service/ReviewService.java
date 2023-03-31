package com.samgyeobsal.service;

import com.samgyeobsal.domain.review.InsertReviewDTO;
import com.samgyeobsal.domain.review.ReplyReviewVO;

public interface ReviewService {

    void insertReview(InsertReviewDTO insertReviewDTO);

    void replyReview(ReplyReviewVO replyReviewVO);

    boolean isAlreadyExistReview(String rtype, String memail,String fid);
}
