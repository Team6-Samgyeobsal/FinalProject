package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface FundingMapper {

    FundingVO getFundingList();

}
