package com.samgyeobsal.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Test
    void getReviewScore(){
        int score = commonService.getReviewScore("재밌어요");
        log.info("score = {}", score);

    }

}
