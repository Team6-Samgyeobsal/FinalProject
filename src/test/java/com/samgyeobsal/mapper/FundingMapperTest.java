package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class FundingMapperTest {

    @Autowired
    FundingMapper fundingMapper;

//    @Test
//    public void getFundingListTest() {
//        List<FundingVO> list = fundingMapper.getFundingList();
//        list.forEach(funding -> log.info(funding));
//    }
}
