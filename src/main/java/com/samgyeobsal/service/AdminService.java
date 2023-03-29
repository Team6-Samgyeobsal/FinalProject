package com.samgyeobsal.service;

import com.samgyeobsal.domain.admin.*;
import com.samgyeobsal.domain.funding.ReviewVO;

import java.util.List;

public interface AdminService {

    List<FundingDocumentDTO> getDocumentList();

    void updateDocumentStatus(UpdateDocumentDTO document);

    List<ReviewVO> getAllReviewList(int page, int size);

    void deleteReview(DeleteReviewDTO reviewDTO);

    List<TotalSaleDTO> getHyundaiTotalSale();

    List<DailySaleDTO> getRecentDailySaleListByHyundai(String tid);

    List<CategorySale> getRecentCategorySaleListByHyundai(String tid);

    void promoteFundingToStore(String fid);

    int getReviewCount();
}
