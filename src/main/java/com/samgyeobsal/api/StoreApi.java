package com.samgyeobsal.api;

import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.service.FundingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "입점매장 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreApi {

    private final FundingService fundingService;

    @Operation(summary = "스토어의 상세 정보 리턴")
    @Parameter(name = "fid", description = "매장아이디")
    @GetMapping("/{fid}")
    public ResponseEntity<FundingDetailVO> getStoreByFid(
            @PathVariable("fid") String fid){
        FundingDetailVO store = fundingService.getFundingDetail(fid, "STORE");
        return new ResponseEntity<>(store, HttpStatus.OK);
    }
}
