package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.*;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;
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
    public FundingDetailVO getFundingDetail(String fid,String fstatus) {
        return fundingMapper.getFundingDetail(fid,fstatus);
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

    @Override
    public ReviewCountVO reviewCount (String fid){
        return fundingMapper.reviewCount(fid);
    }

    @Override
    public int getFundingTotalCount(FundingCriteria fundingCriteria) {
        return fundingMapper.getFundingTotalCount(fundingCriteria);
    }

}
