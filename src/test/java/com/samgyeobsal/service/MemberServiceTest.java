package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.LoginDTO;
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

    @Test
    void login(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("user@gmail.com");
        loginDTO.setPassword("1111");
        memberService.login(loginDTO);
    }
}
