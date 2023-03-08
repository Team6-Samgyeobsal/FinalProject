package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface FundingMapper {

    List<FundingVO> getFundingList(@Param("criteria") FundingCriteria criteria);

    FundingDetailVO getFundingDetail(@Param("fid") String fid);

    List<ReviewVO> getReviewList(@Param("criteria") ReviewCriteria criteria);
}