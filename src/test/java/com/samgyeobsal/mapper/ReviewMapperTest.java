package com.samgyeobsal.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ReviewMapperTest {

    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    void isWritableStoreReview() {
        String email = "user@gmail.com";
        String oid = "1";
        Boolean writable = reviewMapper.isWritableStoreReview(email, oid);
        log.info("writable = {}", writable);
    }
}
