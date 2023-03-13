package com.samgyeobsal.api;

import com.samgyeobsal.domain.funding.*;
import com.samgyeobsal.service.FundingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/funding")
@Log4j2
public class FundingApi {

    @Autowired
    private FundingService fundingService;

    @GetMapping("/list")
    public ResponseEntity<List<FundingVO>> getFundingList(@ModelAttribute FundingCriteria criteria) {
        log.info(criteria);
        List<FundingVO> list= fundingService.getFundingList(criteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<List<ReviewVO>> getReviewList(@ModelAttribute ReviewCriteria criteria) {
        log.info("-----------------criteria ="+criteria);

        List<ReviewVO> review= fundingService.getReviewList(criteria);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/{fundingId}/product")
    public ResponseEntity<List<ProductVO>> getProductList(
            @PathVariable("fundingId") String fundingId ){
        List<ProductVO> products = fundingService.getProductListByFundingId(fundingId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{fundingId}/product/{productId}")
    public ResponseEntity<ProductVO> getProduct(
            @PathVariable("fundingId") String fundingId,
            @PathVariable("productId") String productId
    ){
        ProductVO productVO = fundingService.getProductByFundingIdAndProductId(fundingId, productId);
        return new ResponseEntity<>(productVO, HttpStatus.OK);

    }
}
