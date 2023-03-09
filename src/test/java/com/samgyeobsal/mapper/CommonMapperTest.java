package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CommonMapperTest {

    @Autowired
    private CommonMapper commonMapper;

    @Test
    void getActiveCompetitionList(){
        List<CompetitionHyundaiVO> activeCompetitionList = commonMapper.getActiveCompetitionList();
        log.info("comList = {}", activeCompetitionList);
    }

    @Test
    void getCompetitionByCidAndTid(){
        String cid = "session1";
        String tid = "1";
        CompetitionHyundaiVO competition = commonMapper.getCompetitionByCidAndTid(cid, tid);
        log.info("competition = {}", competition);

    }
}
