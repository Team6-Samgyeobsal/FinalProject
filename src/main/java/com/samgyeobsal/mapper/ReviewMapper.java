package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.review.InsertReviewDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

    Boolean isWritableStoreReview(String email, String orderId);

    int insertReview(InsertReviewDTO insertReviewDTO);

}
