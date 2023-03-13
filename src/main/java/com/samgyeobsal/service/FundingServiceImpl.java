package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.*;
import com.samgyeobsal.mapper.FundingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundingServiceImpl implements FundingService{

    @Autowired
    private FundingMapper fundingMapper;

    @Override
    public List<FundingVO> getFundingList(FundingCriteria criteria){
        return fundingMapper.getFundingList(criteria);
    }

    @Override
    public FundingDetailVO getFundingDetail(String fid) {
        return fundingMapper.getFundingDetail(fid);
    }

    @Override
    public List<ReviewVO> getReviewList(ReviewCriteria criteria) {
        return fundingMapper.getReviewList(criteria);
    }

    @Override
    public List<ProductVO> getProductListByFundingId(String fundingId) {
        return fundingMapper.findProductListByFundingId(fundingId);
    }

    @Override
    public ProductVO getProductByFundingIdAndProductId(String fundingId, String productId) {
        return fundingMapper.findProductByFundingIdAndProductId(fundingId,productId);
    }
}
