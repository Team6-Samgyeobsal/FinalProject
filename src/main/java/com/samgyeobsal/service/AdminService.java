package com.samgyeobsal.service;

import com.samgyeobsal.domain.admin.DeleteReviewDTO;
import com.samgyeobsal.domain.admin.FundingDocumentDTO;
import com.samgyeobsal.domain.admin.UpdateDocumentDTO;
import com.samgyeobsal.domain.funding.ReviewVO;

import java.util.List;

public interface AdminService {

    List<FundingDocumentDTO> getDocumentList();

    void updateDocumentStatus(UpdateDocumentDTO document);

    List<ReviewVO> getAllReviewList();

    void deleteReview(DeleteReviewDTO reviewDTO);
}
