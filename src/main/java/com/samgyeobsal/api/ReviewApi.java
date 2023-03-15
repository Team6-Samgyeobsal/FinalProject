package com.samgyeobsal.api;

import com.samgyeobsal.security.domain.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReviewApi {

    @GetMapping("/funding/{fundingId}/review/isExist")
    public ResponseEntity<Boolean> fundingReviewExist(
            @AuthenticationPrincipal Account account,
            @PathVariable("fundingId") String fundingId
    ){
        if(account == null) return new ResponseEntity<>(true, HttpStatus.OK);
        // TODO : 펀딩아이디와 멤버 이메일로 이미 댓글을 쓴 적있는지 확인
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
