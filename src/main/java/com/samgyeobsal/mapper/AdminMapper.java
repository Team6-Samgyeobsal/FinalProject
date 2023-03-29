package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.admin.*;
import com.samgyeobsal.domain.funding.ReviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<FundingDocumentDTO> getDocumentList();

    int updateDocumentStatus(UpdateDocumentDTO updateDocument);

    List<ReviewVO> getAllReviewList(int page, int size);

    int deleteReview(DeleteReviewDTO reviewDTO);

    List<TotalSaleDTO> getHyundaiTotalSaleList();

    List<DailySaleDTO> getRecentDailySaleListByHyundai(String tid);

    List<CategorySale> getRecentCategorySaleListByHyundai(String tid);

    void updateFundingStatus(String fid, String fstatus);

    int getReviewCount();
}
