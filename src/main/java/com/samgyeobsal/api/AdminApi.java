package com.samgyeobsal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samgyeobsal.domain.admin.*;
import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.AdminService;
import com.samgyeobsal.service.FundingService;
import com.samgyeobsal.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> getAllFundingListByPlace(
            @ModelAttribute FundingCriteria fundingCriteria){
        log.info("fundingCriteria = {}", fundingCriteria);

        List<FundingVO> fundingList = fundingService.getFundingList(fundingCriteria);
        int fundingCount = fundingService.getFundingTotalCount(fundingCriteria);
        Map<String, Object> map = new HashMap<>();

        map.put("fundingList", fundingList);
        map.put("fundingCount", fundingCount);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/funding/{fid}/promote")
    public ResponseEntity<String> promoteFundingToStore(
            @PathVariable("fid") String fid,
            @AuthenticationPrincipal Account account){
        adminService.promoteFundingToStore(fid, account.getMember().getMemail());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<?> getAllReviewList(
            @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "3")int size){
        List<ReviewVO> reviewList = adminService.getAllReviewList(page,size);
        int reviewCount = adminService.getReviewCount();

        Map<String, Object> map = new HashMap<>();
        map.put("reviewList", reviewList);
        map.put("reviewCount", reviewCount);

        return new ResponseEntity<>(map, HttpStatus.OK);
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
