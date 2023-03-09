package com.samgyeobsal.api;

import com.samgyeobsal.domain.member.LoginDTO;
import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.security.provider.JwtTokenProvider;
import com.samgyeobsal.security.service.FormUserDetailService;
import com.samgyeobsal.service.MemberService;
import com.samgyeobsal.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/account")
public class AccountApi {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/dupCheck")
    public ResponseEntity<Boolean> dupCheck(
            @RequestParam("email") String email){
        boolean isExist = memberService.isExist(email);
        return new ResponseEntity<>(isExist, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Validated  @RequestBody LoginDTO loginDTO,
            BindingResult bindingResult) {
        MemberVO member = memberService.login(loginDTO);
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        HashMap<String, Object> res = new HashMap<>();
        String accessToken = jwtTokenProvider.createAccessToken(loginDTO.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(loginDTO.getEmail());
        member.setMpassword(null);

        res.put("member", member);
        res.put("accessToken", accessToken);
        res.put("refreshToken", refreshToken);

        RefreshTokenVO refreshTokenVO = new RefreshTokenVO();
        refreshTokenVO.setMemail(loginDTO.getEmail());
        refreshTokenVO.setRef_token(refreshToken);
        refreshTokenService.insertRefreshToken(refreshTokenVO);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
