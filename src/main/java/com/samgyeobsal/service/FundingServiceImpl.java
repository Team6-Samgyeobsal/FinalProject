package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.*;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;
import com.samgyeobsal.mapper.FundingMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
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

    @Override
    public List<FundingVO> p_funding(FundingCriteria fundingCriteria) {

        Map<String, Object> map = new HashMap<>();
        map.put("vsort",fundingCriteria.getSort());
        map.put("vtype",fundingCriteria.getType());
        map.put("vpage",fundingCriteria.getPage());
        map.put("vtid",fundingCriteria.getTid());
        fundingMapper.p_funding(map);
        log.info(map.toString());
        List<FundingVO> list = (List<FundingVO>) map.get("POUT");
        log.info("ffcc"+list);
        return list;
    }

}
