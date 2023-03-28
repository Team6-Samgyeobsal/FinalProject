package com.samgyeobsal.service;

import com.google.zxing.WriterException;
import com.samgyeobsal.domain.admin.*;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.mapper.AdminMapper;
import com.samgyeobsal.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    private final QrCodeService qrCodeService;

    private final OrderMapper orderMapper;

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

    @Override
    public List<TotalSaleDTO> getHyundaiTotalSale() {
        return adminMapper.getHyundaiTotalSaleList();
    }

    @Override
    public List<DailySaleDTO> getRecentDailySaleListByHyundai(String tid) {
        return adminMapper.getRecentDailySaleListByHyundai(tid);
    }

    @Override
    public List<CategorySale> getRecentCategorySaleListByHyundai(String tid) {
        return adminMapper.getRecentCategorySaleListByHyundai(tid);
    }

    @Override
    @Transactional
    public void promoteFundingToStore(String fid) {
        adminMapper.updateFundingStatus(fid,"STORE");
        List<String> orderIdList = orderMapper.findOrderIdListByFundingId(fid);
        for (String oId : orderIdList) {
            try {
                qrCodeService.generateQrCode(oId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WriterException e) {
                log.info("promoteFundingToStore Erorr occur");
                throw new RuntimeException(e);
            }
        }
    }
}
