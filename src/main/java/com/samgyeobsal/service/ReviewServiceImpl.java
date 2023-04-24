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
        if(score < 0) throw new RuntimeException("Review 욕설 포함");
        int row = reviewMapper.insertReview(insertReviewDTO);
        if(row == 0) throw new RuntimeException("review Mapper Error");
    }
    /**
     * 리뷰 답글 정보 입력
     * @param replyReviewVO
     */
    @Override
    public void replyReview(ReplyReviewVO replyReviewVO) {
        int row=reviewMapper.replyReview(replyReviewVO);
        if(row == 0) throw new RuntimeException("replyReview Mapper Error");
    }

    /**
     * 해당 회원의 해당 펀딩 리뷰 작성유무 리턴
     * @param rtype : 리뷰 종류 (응원 댓글/ 스토어)
     * @param memail : 이메일
     * @param fid : 펀딩 아이디
     */
    @Override
    public boolean isAlreadyExistReview(String rtype, String memail,String fid) {
        return reviewMapper.isAlreadyExistReview(rtype, memail,fid);
    }

    /**
     * 해당 회원의 해당 펀딩 리뷰 삭제
     * @param memail : 이메일
     */
    @Override
    public void deleteReview(String memail) {
        reviewMapper.deleteReview(memail);
    }
}
