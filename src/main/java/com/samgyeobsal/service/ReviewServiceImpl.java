package com.samgyeobsal.service;

import com.samgyeobsal.domain.review.InsertReviewDTO;
import com.samgyeobsal.domain.review.ReplyReviewVO;
import com.samgyeobsal.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewMapper reviewMapper;
    private final CommonService commonService;


    @Override
    @Transactional
    public void insertReview(InsertReviewDTO insertReviewDTO) {

        boolean res = isAlreadyExistReview(insertReviewDTO.getRtype(), insertReviewDTO.getMemail(),insertReviewDTO.getFid());
        if(res) throw new RuntimeException("already exist review");

        int score = commonService.getReviewScore(insertReviewDTO.getRcontent());
        insertReviewDTO.setRscore(score);
        int row = reviewMapper.insertReview(insertReviewDTO);
        if(row == 0) throw new RuntimeException("review Mapper Error");
    }

    @Override
    public void replyReview(ReplyReviewVO replyReviewVO) {
        int row=reviewMapper.replyReview(replyReviewVO);
        if(row == 0) throw new RuntimeException("replyReview Mapper Error");
    }

    @Override
    public boolean isAlreadyExistReview(String rtype, String memail,String fid) {
        return reviewMapper.isAlreadyExistReview(rtype, memail,fid);
    }
}
