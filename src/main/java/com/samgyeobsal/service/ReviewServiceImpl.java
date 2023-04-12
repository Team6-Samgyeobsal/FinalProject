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


    /**
     * 리뷰 정보 삽입
     * 회원당 펀딩 별 리뷰 작성 1회 제한
     * @param insertReviewDTO
     */
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

    /**
     * 해당 회원의 해당 펀딩 리뷰 작성유무 리턴
     */
    @Override
    public boolean isAlreadyExistReview(String rtype, String memail,String fid) {
        return reviewMapper.isAlreadyExistReview(rtype, memail,fid);
    }

    @Override
    public void deleteReview(String memail) {
        reviewMapper.deleteReview(memail);
    }
}
