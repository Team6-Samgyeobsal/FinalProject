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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "관리자 API")
@Slf4j
public class AdminApi {

    private final AdminService adminService;
    private final FundingService fundingService;

    @Operation(summary = "펀딩 서류 통과 여부 결정", description = "펀딩서류 상태를 FAIL 또는 FUNDDING으로 변경합니다.")
    @PostMapping("/document")
    public ResponseEntity<String> updateDocumentStatus(
            @RequestBody UpdateDocumentDTO document){
        log.info("document = {}", document);

        adminService.updateDocumentStatus(document);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Operation(summary = "장소에 따른 진행 중인 펀딩 리턴", description = "더 현대 장소별 진행 중인 펀딩들을 보여줍니다.")
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

    @Operation(summary = "입점할 펀딩 선정", description = "펀딩에 성공한 프로젝트의 상태를 변경합니다.")
    @Parameter(name = "fid", description = "펀딩 아이디")
    @PostMapping("/funding/{fid}/promote")
    public ResponseEntity<String> promoteFundingToStore(
            @PathVariable("fid") String fid,
            @AuthenticationPrincipal Account account){
        adminService.promoteFundingToStore(fid, account.getMember().getMemail());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Operation(summary = "작성된 리뷰 반환", description = "최신 순으로 정렬된 리뷰들을 반환합니다.")
    @Parameters({@Parameter(name = "page", description = "보여줄 페이지 수"),
            @Parameter(name = "size", description = "한 페이지에 보여줄 리뷰 수")})
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

    @Operation(summary = "리뷰 삭제", description = "부적절한 리뷰를 선택해 삭제합니다.")
    @PostMapping("/review/delete")
    public ResponseEntity<String> deleteReview(
            @RequestBody DeleteReviewDTO reviewDTO){
        log.info("reviewDto = {}", reviewDTO);

        adminService.deleteReview(reviewDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Operation(summary = "한달 총 매출", description = "더 현대 장소별 한달 총 매출을 리턴합니다.")
    @GetMapping("/totalSale")
    public ResponseEntity<List<TotalSaleDTO>> getHyundaiTotalSale() {
        List<TotalSaleDTO> hyundaiTotalSale = adminService.getHyundaiTotalSale();
        for (TotalSaleDTO saleDTO : hyundaiTotalSale) {
            log.info("saleDTO = {}", saleDTO);
        }
        return new ResponseEntity<>(hyundaiTotalSale, HttpStatus.OK);
    }

    @Operation(summary = "장소 별 일별 총 매출", description = "더 현대 장소 별 일주일 간 하루 매출을 리턴합니다.")
    @Parameter(name = "tid", description = "장소 아이디")
    @GetMapping("/dailySale")
    public ResponseEntity<List<DailySaleDTO>> getRecentDailySaleListByHyundai(@RequestParam(required = false) String tid){
        List<DailySaleDTO> dailySaleList = adminService.getRecentDailySaleListByHyundai(tid);
        return new ResponseEntity<>(dailySaleList, HttpStatus.OK);
    }

    @Operation(summary = "카테고리 별 일별 매출", description = "카테고리 별 일주일 간 하루 매출을 리턴합니다.")
    @Parameter(name = "tid", description = "장소 아이디")
    @GetMapping("/categorySale")
    public ResponseEntity<List<CategorySale>> getRecentCategorySaleListByHyundai(@RequestParam(required = false) String tid) {
        List<CategorySale> categorySaleList = adminService.getRecentCategorySaleListByHyundai(tid);
        return new ResponseEntity<>(categorySaleList, HttpStatus.OK);
    }

    @Operation(summary = "장소 별 입정 중인 스토어 리턴", description = "장소 별로 입점해 영업 중인 매장 목록을 리턴합니다.")
    @Parameter(name = "tid", description = "장소 아이디")
    @GetMapping("/store")
    public ResponseEntity<List<FundingDocumentDTO>> getStoreListByTid(@RequestParam("tid") String tid) {
        List<FundingDocumentDTO> stores = adminService.getStoreByTid(tid);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @Operation(summary = "입점 매장 취소", description = "입점 중인 매장을 취소합니다.")
    @Parameter(name = "fid", description = "스토어 아이디")
    @PostMapping("/funding/{fid}/cancel")
    public ResponseEntity<String> cancelStore(
            @PathVariable("fid") String fid){
        UpdateDocumentDTO updateDTO = new UpdateDocumentDTO();
        updateDTO.setFid(fid);
        updateDTO.setAdditionalStatus("END");
        adminService.updateDocumentStatus(updateDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
