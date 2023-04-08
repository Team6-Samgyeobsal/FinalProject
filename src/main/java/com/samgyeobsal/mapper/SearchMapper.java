package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.search.SearchCriteria;
import com.samgyeobsal.domain.search.SearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<SearchVO> getSearchList(@Param("criteria") SearchCriteria criteria);
    int countSearch(@Param("criteria") SearchCriteria criteria);
}
