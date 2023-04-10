package com.samgyeobsal.service;

import com.google.zxing.WriterException;
import com.samgyeobsal.domain.admin.*;
import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private final OrderService orderService;
    private final KaKaoMessageService kaKaoMessageService;
    private final FundingService fundingService;

    @Value("${user.email}")
    private String userEmail;

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
    public List<ReviewVO> getAllReviewList(int page, int size) {
        return adminMapper.getAllReviewList(page,size);
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
    public void promoteFundingToStore(String fid, String memail) {
        List<String> orderIdList = orderService.getOrderIdListByFundingId(fid);
        FundingDetailVO store = fundingService.getFundingDetail(fid, "FUNDING");
        adminMapper.updateFundingStatus(fid,"STORE");
        // 시연을 위함
        adminMapper.updateFundingCompetition(fid, "season3");
        log.info("store = {}", store);
        for (String oId : orderIdList) {
            try {
                OrderVO order = orderService.getOrderByOrderId(oId);

                qrCodeService.generateQrCode(oId);

                 // 주문자가 user@gmail.com 일 경우에만 메시지 보내기
                if(order.getMemail().equals(userEmail)){
                    kaKaoMessageService.sendOrderInfoByKakaoMessage(memail, order, store);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WriterException e) {
                log.info("promoteFundingToStore Erorr occur");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getReviewCount() {
        return adminMapper.getReviewCount();
    }

    @Override
    public List<FundingDocumentDTO> getStoreByTid(String tid) {
        return adminMapper.getStoreListByTid(tid);
    }
}
