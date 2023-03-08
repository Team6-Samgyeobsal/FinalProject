package com.samgyeobsal.api;

import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
public class MemberApi {

    private final MemberService memberService;

    @GetMapping("/funding")
    public ResponseEntity<?> findMember(@AuthenticationPrincipal Account account){
        List<FundingVO> findingListByEmail = memberService.findFindingListByEmail(account.getMember().getMemail());

        return new ResponseEntity<>(findingListByEmail, HttpStatus.OK);
    }
}
