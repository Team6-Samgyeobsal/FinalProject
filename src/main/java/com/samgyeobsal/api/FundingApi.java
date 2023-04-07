package com.samgyeobsal.api;

import com.samgyeobsal.domain.funding.*;
import com.samgyeobsal.domain.maker.UpdateFundingProductDTO;
import com.samgyeobsal.domain.review.InsertReviewDTO;
import com.samgyeobsal.domain.review.ReplyReviewVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.FundingService;
import com.samgyeobsal.service.MakerService;
import com.samgyeobsal.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funding")
@Tag(name = "펀딩 API")
@RequiredArgsConstructor
@Log4j2
public class FundingApi {

    private final FundingService fundingService;
    private final MakerService makerService;

    private final ReviewService reviewService;

    @Operation(summary = "펀딩 생성", description = "로그인한 유저의 펀딩을 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createFunding(@AuthenticationPrincipal Account account){
        String memail = account.getMember().getMemail();
        String fid = makerService.createFunding(memail);
        return new ResponseEntity<>(fid, HttpStatus.OK);
    }

    @Operation(summary = "조건에 따른 펀딩 리턴", description = "카테고리, 장소, 정렬에 따른 펀딩을 리턴합니다.")
    @GetMapping("/list")
    public ResponseEntity<List<FundingVO>> getFundingList(@ModelAttribute FundingCriteria criteria) {
        log.info(criteria);
        List<FundingVO> list= fundingService.getFundingList(criteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "조건에 따른 리뷰 리턴", description = "정렬, 타입에 따라 펀딩의 리뷰를 리턴합니다.")
    @GetMapping("/review")
    public ResponseEntity<Map<String,Object>> getReviewList(@ModelAttribute ReviewCriteria criteria) {
        log.info("-----------------criteria ="+criteria);
        ReviewCountVO countVO=fundingService.reviewCount(criteria.getFid());
        List<ReviewVO> review= fundingService.getReviewList(criteria);
        log.info("reviews = {}", review);

        Map<String,Object> res = new HashMap<>();
        res.put("reviewCount",countVO);
        res.put("review",review);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "펀딩 상품 리스트 리턴", description = "해당 펀딩의 상품들을 리턴합니다.")
    @Parameter(name = "fundingId", description = "펀딩아이디")
    @GetMapping("/{fundingId}/product")
    public ResponseEntity<List<ProductVO>> getProductList(
            @PathVariable("fundingId") String fundingId ){
        List<ProductVO> products = fundingService.getProductListByFundingId(fundingId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "특정 펀딩 상품 리턴", description = "해당 펀딩의 해당 상품을 리턴합니다.")
    @Parameter(name = "fundingId", description = "펀딩아이디")
    @Parameter(name = "productId", description = "상품아이디")
    @GetMapping("/{fundingId}/product/{productId}")
    public ResponseEntity<ProductVO> getProduct(
            @PathVariable("fundingId") String fundingId,
            @PathVariable("productId") String productId
    ){
        ProductVO productVO = fundingService.getProductByFundingIdAndProductId(fundingId, productId);
        return new ResponseEntity<>(productVO, HttpStatus.OK);
    }

    @Operation(summary = "특정 펀딩 상품 수정", description = "해당 펀딩의 해당 상품을 리턴합니다.")
    @Parameter(name = "fundingId", description = "펀딩아이디")
    @PostMapping("/{fundingId}/product")
    public ResponseEntity<String> editProduct(
            @PathVariable("fundingId") String fundingId,
            @RequestBody UpdateFundingProductDTO fundingReward
    ){
        fundingReward.setFid(fundingId);
        log.info("fundingReward = {}", fundingReward);
        makerService.updateFundingProduct(fundingReward);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Operation(summary = "특정 상품 상품 삭제", description = "해당 펀딩의 해당 상품을 삭제합니다.")
    @Parameter(name = "fundingId", description = "펀딩아이디")
    @PostMapping("/{fundingId}/product/remove")
    public ResponseEntity<String> removeProduct(
            @PathVariable("fundingId") String fundingId, @RequestBody Map<String,String> map){
        String fpid = map.get("fpid");

        makerService.deleteFundingProduct(fpid);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Operation(summary = "펀딩 댓글 생성", description = "해당 펀딩에 댓글을 생성합니다.")
    @Parameter(name = "fundingId", description = "펀딩아이디")
    @PostMapping("/{fundingId}/product/review")
    public ResponseEntity<String> fundingReview(
            @PathVariable("fundingId") String fundingId,
            @RequestBody InsertReviewDTO insertReviewDTO,
            @AuthenticationPrincipal Account account)
    {
        String memail=account.getMember().getMemail();
        insertReviewDTO.setMemail(memail);

        reviewService.insertReview(insertReviewDTO);
        log.info("insertReviewDTO"+insertReviewDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Operation(summary = "펀딩 리뷰 답글", description = "펀딩 소유자인 유저가 댓글에 답글을 생성합니다.")
    @Parameter(name = "fundingId", description = "펀딩아이디")
    @PostMapping("/{fundingId}/product/replyReview")
    public ResponseEntity<String> fundingReplyReview(
            @PathVariable("fundingId") String fundingId,
            @RequestBody ReplyReviewVO replyReviewVO)
    {
        System.out.println(">>>>>>>"+replyReviewVO.getRecontent());
        reviewService.replyReview(replyReviewVO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
