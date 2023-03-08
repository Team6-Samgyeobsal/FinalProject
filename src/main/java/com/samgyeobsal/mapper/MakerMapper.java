package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.maker.FundingMakerVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MakerMapper {

    FundingMakerVO findFundingMakerByFundingId(String fundingId);

}
