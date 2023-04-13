package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.*;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface FundingMapper {

    /**
     * @deprecated 프로시저 사용
     */
    @Deprecated
    List<FundingVO> getFundingList(@Param("criteria") FundingCriteria criteria);

    FundingDetailVO getFundingDetail(@Param("fid") String fid,@Param("fstatus") String fstatus);

    List<ReviewVO> getReviewList(@Param("criteria") ReviewCriteria criteria);

    List<ProductVO> findProductListByFundingId(String fundingId);

    ProductVO findProductByFundingIdAndProductId(String fundingId, String productId);

    ReviewCountVO reviewCount (String fid);

    int getFundingTotalCount(@Param("criteria") FundingCriteria fundingCriteria);

    void p_funding(Map<String,Object> map);
}
