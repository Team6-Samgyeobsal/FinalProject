package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.sample.SampleVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleMapperTest {

    @Autowired
    private SampleMapper sampleMapper;

    @Test
    void getSample(){
        SampleVO sample = sampleMapper.getSample();
        Assertions.assertEquals(sample.getCol1(), "1");
        Assertions.assertEquals(sample.getCol2(), "2");

    }
}
