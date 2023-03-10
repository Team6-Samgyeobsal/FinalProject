package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingImgVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MakerMapper {

    FundingMakerVO findFundingMakerByFundingId(String fundingId);

    int updateFundingBaseInfo(FundingBaseInfoDTO baseInfo);

    List<FundingImgVO> findFundingImgListByFundingId(String fundingId);

}
