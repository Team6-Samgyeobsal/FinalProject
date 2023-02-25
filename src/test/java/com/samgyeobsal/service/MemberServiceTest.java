package com.samgyeobsal.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void isExist(){
        String existEmail = "user1@email.com";
        String notExistEmail = "temp@email.com";

        boolean result1 = memberService.isExist(existEmail);
        Assertions.assertTrue(result1);

        boolean result2 = memberService.isExist(notExistEmail);
        Assertions.assertFalse(result2);

    }
}
