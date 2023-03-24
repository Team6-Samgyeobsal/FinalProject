package com.samgyeobsal.api;

import com.samgyeobsal.domain.admin.*;
import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.service.AdminService;
import com.samgyeobsal.service.FundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminApi {

    private final AdminService adminService;
    private final FundingService fundingService;

    @PostMapping("/document")
    public ResponseEntity<String> updateDocumentStatus(
            @RequestBody UpdateDocumentDTO document){
        log.info("document = {}", document);

        adminService.updateDocumentStatus(document);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/funding")
    public ResponseEntity<List<FundingVO>> getAllFundingListByPlace(
            @ModelAttribute FundingCriteria fundingCriteria){
        log.info("fundingCriteria = {}", fundingCriteria);

        List<FundingVO> fundingList = fundingService.getFundingList(fundingCriteria);
        log.info("fundingList = {}", fundingList);
        return new ResponseEntity<>(fundingList, HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<List<ReviewVO>> getAllReviewList(){
        List<ReviewVO> reviewList = adminService.getAllReviewList();
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    @PostMapping("/review/delete")
    public ResponseEntity<String> deleteReview(
            @RequestBody DeleteReviewDTO reviewDTO){
        log.info("reviewDto = {}", reviewDTO);

        adminService.deleteReview(reviewDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/totalSale")
    public ResponseEntity<List<TotalSaleDTO>> getHyundaiTotalSale() {
        List<TotalSaleDTO> hyundaiTotalSale = adminService.getHyundaiTotalSale();
        for (TotalSaleDTO saleDTO : hyundaiTotalSale) {
            log.info("saleDTO = {}", saleDTO);
        }
        return new ResponseEntity<>(hyundaiTotalSale, HttpStatus.OK);
    }

    @GetMapping("/dailySale")
    public ResponseEntity<List<DailySaleDTO>> getRecentDailySaleListByHyundai(@RequestParam(required = false) String tid){
        List<DailySaleDTO> dailySaleList = adminService.getRecentDailySaleListByHyundai(tid);
        return new ResponseEntity<>(dailySaleList, HttpStatus.OK);
    }

    @GetMapping("/categorySale")
    public ResponseEntity<List<CategorySale>> getRecentCategorySaleListByHyundai(@RequestParam(required = false) String tid) {
        List<CategorySale> categorySaleList = adminService.getRecentCategorySaleListByHyundai(tid);
        return new ResponseEntity<>(categorySaleList, HttpStatus.OK);
    }


}
