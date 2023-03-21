package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.fame.FameCriteria;
import com.samgyeobsal.domain.fame.FameVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class FameMapperTest {

    @Autowired
    FameMapper fameMapper;

    @Test
    public void getFameListTest(){
        FameCriteria criteria = new FameCriteria();
        criteria.setPage(0);
        criteria.setType("1");
        List<FameVO> list = fameMapper.getFameList(criteria);
        list.forEach(fame -> log.info(fame));
    }
}
