package com.samgyeobsal.api;

import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.funding.ReviewCriteria;
import com.samgyeobsal.domain.funding.ReviewVO;
import com.samgyeobsal.service.FundingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
