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
    @Override
    public List<SearchVO> getSearchList(SearchCriteria criteria) {
        return searchMapper.getSearchList(criteria);
    }

    @Override
    public int countSearch(SearchCriteria criteria) {
        return searchMapper.countSearch(criteria);
    }
}
