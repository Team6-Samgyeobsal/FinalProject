package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.FundingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface FundingMapper {

    List<FundingVO> getFundingList(@Param("criteria") FundingCriteria criteria);

}
