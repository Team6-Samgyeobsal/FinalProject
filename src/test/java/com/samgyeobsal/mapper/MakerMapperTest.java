package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingImgVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Test
    @Transactional
    void updateFundingBaseInfo(){
        String thumb = "testThumb";
        FundingBaseInfoDTO baseInfo = new FundingBaseInfoDTO();
        baseInfo.setFstore_name("storeName");
        baseInfo.setFid("1");
        baseInfo.setFthumb(thumb);
        baseInfo.setCtid("2");
        baseInfo.setCid("session2");

        makerMapper.updateFundingBaseInfo(baseInfo);

        FundingMakerVO fundingMaker = makerMapper.findFundingMakerByFundingId("1");
        Assertions.assertEquals(thumb, fundingMaker.getFthumb());
    }

    @Test
    void findFundingImgByFundingId(){
        String fundingId = "111";
        List<FundingImgVO> fundingImg = makerMapper.findFundingImgListByFundingId(fundingId);
        log.info("fundingImg = {}", fundingImg);

    }


}
