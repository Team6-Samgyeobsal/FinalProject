package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.search.SearchCriteria;
import com.samgyeobsal.domain.search.SearchVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class SearchMapperTest {

    @Autowired
    SearchMapper searchMapper;
    @Test
    public void getSearchList(){
        SearchCriteria criteria=new SearchCriteria();
        criteria.setFstatus("FUNDING");
        criteria.setPage(0);
        criteria.setKeyword("가");
        List<SearchVO> list=searchMapper.getSearchList(criteria);
        list.forEach(search -> log.info("search "+search));
    }
}
