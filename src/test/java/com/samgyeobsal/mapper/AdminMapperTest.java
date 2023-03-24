package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.admin.*;
import com.samgyeobsal.domain.funding.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminMapperTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    void getDocumentList(){
        List<FundingDocumentDTO> documentList = adminMapper.getDocumentList();
        for (FundingDocumentDTO document : documentList) {
            log.info("document = {}", document);

        }
    }

    @Transactional
    @Test
    void updateDocumentStatus(){
        String fid = "d5c1a8a5-6466-4488-8792-66e39e26ff1e";
        UpdateDocumentDTO document = new UpdateDocumentDTO();
        document.setFid(fid);
        document.setIsPass(true);

        int i = adminMapper.updateDocumentStatus(document);
        log.info("row = {}", i);
    }

    @Test
    void getAllReviewList(){
        List<ReviewVO> allReviewList = adminMapper.getAllReviewList();
        for (ReviewVO reviewVO : allReviewList) {
            log.info("review = {}", reviewVO);
        }
    }

    @Test
    void getHyundaiTotalSale(){
        List<TotalSaleDTO> hyundaiTotalSale = adminMapper.getHyundaiTotalSaleList();
        for (TotalSaleDTO hyundai : hyundaiTotalSale) {
            log.info("hyundai = {}", hyundai);
        }
    }

    @Test
    void getRecentDailySaleListByHyundai(){
        List<DailySaleDTO> recentDailySaleListByHyundai = adminMapper.getRecentDailySaleListByHyundai(null);
        for (DailySaleDTO dailySaleDTO : recentDailySaleListByHyundai) {
            log.info("dailySale = {}", dailySaleDTO);
        }
    }

    @Test
    void getRecentCategoryRatioByHyundai(){
        List<CategorySale> categorySaleList = adminMapper.getRecentCategorySaleListByHyundai("1");
        for (CategorySale categorySale : categorySaleList) {
            log.info("categorySale = {}", categorySale);
        }
    }
}
