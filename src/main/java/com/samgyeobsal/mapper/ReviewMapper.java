package com.samgyeobsal.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {

    Boolean isWritableStoreReview(String email, String orderId);
}
