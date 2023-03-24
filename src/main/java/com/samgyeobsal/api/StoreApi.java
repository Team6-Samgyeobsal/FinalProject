package com.samgyeobsal.api;

import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.service.FundingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreApi {

    private final FundingService fundingService;

    @GetMapping("/{fid}")
    public ResponseEntity<FundingDetailVO> getStoreByFid(
            @PathVariable("fid") String fid){
        FundingDetailVO store = fundingService.getFundingDetail(fid, "STORE");
        return new ResponseEntity<>(store, HttpStatus.OK);
    }
}
