package com.samgyeobsal.service;

import com.google.zxing.WriterException;
import com.samgyeobsal.domain.admin.*;
import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.exception.KakaoMeessageException;
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

    /**
     * 펀딩 신청 서류 리스트 리턴
     */
    @Override
    public List<FundingDocumentDTO> getDocumentList() {
        return adminMapper.getDocumentList();
    }

    /**
     * 펀딩 서류 상태(PARTICIPANT) 변경
     * FUNDING : 합격 (진행 중인 경연에 추가)
     * FAIL : 불합격
     */
    @Override
    public void updateDocumentStatus(UpdateDocumentDTO updateDocument) {
        int row = adminMapper.updateDocumentStatus(updateDocument);
        if(row == 0) throw new RuntimeException("update Error occur");
    }

    /**
     * 펀딩 리뷰 리스트 리턴
     * @param page : 페이지 수
     * @param size : 한 페이지에 노출될 리뷰 수
     */
    @Override
    public List<ReviewVO> getAllReviewList(int page, int size) {
        return adminMapper.getAllReviewList(page,size);
    }

    /**
     * 부적절한 댓글 삭제
     */
    @Override
    public void deleteReview(DeleteReviewDTO reviewDTO) {
        int row = adminMapper.deleteReview(reviewDTO);
        if (row == 0) throw new RuntimeException("delete Review Error");
    }

    /**
     * 진행 중인 경연의 장소 별 총 금액 리턴
     */
    @Override
    public List<TotalSaleDTO> getHyundaiTotalSale() {
        return adminMapper.getHyundaiTotalSaleList();
    }

    /**
     * 장소 별 최근 일주일 간 매출 리턴
     * @param tid : 장소 아이디
     */
    @Override
    public List<DailySaleDTO> getRecentDailySaleListByHyundai(String tid) {
        return adminMapper.getRecentDailySaleListByHyundai(tid);
    }

    /**
     * 장소 별 최근 일주일 간 카테고리 매출 리턴
     * @param tid : 장소아이디
     */
    @Override
    public List<CategorySale> getRecentCategorySaleListByHyundai(String tid) {
        return adminMapper.getRecentCategorySaleListByHyundai(tid);
    }

    /**
     * 펀딩 서류 상태 변경 : FUNDING -> STORE
     * 펀딩 구매자에게 카카오톡으로 QR코드 발송
     * @param fid : 펀딩 아아디
     * @param memail : 카카오톡 받을 회원 이메일
     */
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

            } catch (IOException | WriterException e) {
                throw new KakaoMeessageException("kakao message error occur", e);
            }
        }
    }

    /**
     * 리뷰 페이징을 위한 리뷰 총 개수
     */
    @Override
    public int getReviewCount() {
        return adminMapper.getReviewCount();
    }

    /**
     * 장소 별 입점 중인 매장 (STORE) 리스트 리턴
     * @param tid : 장소 아이디
     */
    @Override
    public List<FundingDocumentDTO> getStoreByTid(String tid) {
        return adminMapper.getStoreListByTid(tid);
    }
}
