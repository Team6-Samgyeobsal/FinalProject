package com.samgyeobsal.service;

import com.samgyeobsal.domain.admin.DeleteReviewDTO;
import com.samgyeobsal.domain.admin.FundingDocumentDTO;
import com.samgyeobsal.domain.admin.UpdateDocumentDTO;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    public List<FundingDocumentDTO> getDocumentList() {
        return adminMapper.getDocumentList();
    }

    @Override
    public void updateDocumentStatus(UpdateDocumentDTO updateDocument) {
        int row = adminMapper.updateDocumentStatus(updateDocument);
        if(row == 0) throw new RuntimeException("update Error occur");
    }

    @Override
    public List<ReviewVO> getAllReviewList() {
        return adminMapper.getAllReviewList();
    }

    @Override
    public void deleteReview(DeleteReviewDTO reviewDTO) {
        int row = adminMapper.deleteReview(reviewDTO);
        if (row == 0) throw new RuntimeException("delete Review Error");
    }
}
