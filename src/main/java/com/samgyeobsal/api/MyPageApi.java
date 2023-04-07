package com.samgyeobsal.api;

import com.samgyeobsal.service.MakerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "마이페이지 API")
@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageApi {

    private final MakerService makerService;

    @Operation(summary = "펀딩 서류 제출", description = "펀딩을 시작하기 위해 서류를 관리자에게 제출합니다.")
    @Parameter(name = "fid",description = "펀딩아이디")
    @PostMapping("/maker/funding/{fid}/submit")
    public ResponseEntity<String> submitDocument(@PathVariable("fid") String fid) {
        makerService.submitDocument(fid);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
