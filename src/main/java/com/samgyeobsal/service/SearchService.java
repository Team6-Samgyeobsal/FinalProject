package com.samgyeobsal.service;

import com.samgyeobsal.domain.search.SearchCriteria;
import com.samgyeobsal.domain.search.SearchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchService {

    List<SearchVO> getSearchList(SearchCriteria criteria);

    int countSearch(SearchCriteria criteria);
}
