package com.samgyeobsal.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MakerServiceTest {

    @Autowired
    private MakerService makerService;

    @Test
    void submitDocument(){
        makerService.submitDocument("d0b14b76-0794-4220-832b-13ca709f3174");

    }
}
