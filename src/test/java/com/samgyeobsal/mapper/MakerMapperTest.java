package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.maker.FundingMakerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MakerMapperTest {

    @Autowired
    private MakerMapper makerMapper;

    @Test
    void findFundingMakerByFundingId(){
        String fundingId = "1";
        FundingMakerVO fundingMaker = makerMapper.findFundingMakerByFundingId(fundingId);
        log.info("fundingMaker = {}", fundingMaker);

    }


}
