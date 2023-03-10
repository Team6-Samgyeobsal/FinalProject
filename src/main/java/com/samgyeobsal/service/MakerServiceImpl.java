package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingImgVO;
import com.samgyeobsal.domain.maker.FundingBaseInfoDTO;
import com.samgyeobsal.domain.maker.FundingMakerVO;
import com.samgyeobsal.mapper.MakerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MakerServiceImpl implements MakerService {

    private final MakerMapper makerMapper;

    @Override
    public FundingMakerVO getFundingMakerByFundingId(String fundingId) {
        return makerMapper.findFundingMakerByFundingId(fundingId);
    }

    @Override
    public void updateFundingBaseInfo(FundingBaseInfoDTO baseInfo) {
        int row = makerMapper.updateFundingBaseInfo(baseInfo);
        if(row == 0) throw new RuntimeException("에러 발생");
    }

    @Override
    public List<FundingImgVO> getFundingImgsByFundingId(String fundingId) {
        List<FundingImgVO> fundingImgs = makerMapper.findFundingImgListByFundingId(fundingId);
        return fundingImgs == null ? new ArrayList<>() : fundingImgs;
    }
}
