package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingImgVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;

import java.util.List;

public interface MakerService {

    FundingMakerVO getFundingMakerByFundingId(String fundingId);

    void updateFundingBaseInfo(FundingBaseInfoDTO baseInfo);

    List<FundingImgVO> getFundingImgsByFundingId(String fundingId);



}
