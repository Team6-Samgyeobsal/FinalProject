package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.*;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;

import java.util.List;

public interface FundingService {
    List<FundingVO> getFundingList(FundingCriteria criteria) ;

    FundingDetailVO getFundingDetail(String fid, String fstatus);

    List<ReviewVO>getReviewList(ReviewCriteria criteria);

    List<ProductVO> getProductListByFundingId(String fundingId);

    ProductVO getProductByFundingIdAndProductId(String fundingId, String productId);

}
