package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.*;

import java.sql.SQLException;
import java.util.List;

public interface FundingService {
    List<FundingVO> getFundingList(FundingCriteria criteria) ;

    FundingDetailVO getFundingDetail(String fid);

    List<ReviewVO>getReviewList(ReviewCriteria criteria);

    List<ProductVO> getProductListByFundingId(String fundingId);

    ProductVO getProductByFundingIdAndProductId(String fundingId, String productId);
}
