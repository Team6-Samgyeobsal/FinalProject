package com.samgyeobsal.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
@Slf4j
public class UserDetailsServiceTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    void loadUserByUsername(){
        String email = "user1@email.com";
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        log.info("userDetails = {}", userDetails);
        Assertions.assertEquals(userDetails.getUsername(),email);
    }
}
