package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingImgVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import com.samgyeobsal.domain.maker.FundingStoryDTO;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;

import java.util.List;

public interface MakerService {

    FundingMakerVO getFundingMakerByFundingId(String fundingId);

    void updateFundingBaseInfo(FundingBaseInfoDTO baseInfo);

    List<FundingImgVO> getFundingImgsByFundingId(String fundingId);

    void updateFundingStory(FundingStoryDTO story);

    void updateFundingProduct(UpdateFundingProductDTO product);

}
