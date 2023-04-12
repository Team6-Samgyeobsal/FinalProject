package com.samgyeobsal.service;

import com.samgyeobsal.domain.search.SearchCriteria;
import com.samgyeobsal.domain.search.SearchVO;
import com.samgyeobsal.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService{


    private final SearchMapper searchMapper;

    /**
     * 키워드로 검색한 펀딩 리스트 리턴
     * @param criteria
     */
    @Override
    public List<SearchVO> getSearchList(SearchCriteria criteria) {
        return searchMapper.getSearchList(criteria);
    }

    /**
     * 키워드로 검색한 펀딩 리스트의 수 리턴
     * @param criteria
     */
    @Override
    public int countSearch(SearchCriteria criteria) {
        return searchMapper.countSearch(criteria);
    }
}
