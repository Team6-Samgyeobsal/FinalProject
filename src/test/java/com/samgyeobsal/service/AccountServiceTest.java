package com.samgyeobsal.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void isExist(){
        String existEmail = "user1@email.com";
        String notExistEmail = "temp@email.com";

        boolean result1 = accountService.isExist(existEmail);
        Assertions.assertTrue(result1);

        boolean result2 = accountService.isExist(notExistEmail);
        Assertions.assertFalse(result2);

    }
}
