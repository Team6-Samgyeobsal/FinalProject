package com.samgyeobsal.api;

import com.samgyeobsal.domain.member.LoginDTO;
import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.security.provider.JwtTokenProvider;
import com.samgyeobsal.security.service.FormUserDetailService;
import com.samgyeobsal.service.MemberService;
import com.samgyeobsal.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            BindingResult bindingResult, HttpServletResponse response) {
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

        // 헤더에 accessToken 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        Cookie acsCookie = new Cookie("accessToken", accessToken);
        acsCookie.setPath("/");
        response.addCookie(acsCookie);

        Cookie refCookie = new Cookie("refreshToken", refreshToken);
        acsCookie.setPath("/");
        response.addCookie(refCookie);

        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request, HttpServletResponse response,
            @AuthenticationPrincipal Account account) {

        log.info("accounes = {}", account);

        // 현재 사용자의 쿠키 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accessToken") || cookie.getName().equals("refreshToken")) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        OAuth2TokenVO oAUth2Token = refreshTokenService.getOAuth2TokenByEmail(account.getMember().getMemail());
        String provider = account.getMember().getMloginType();

        log.info("logout provider = {}", provider);

        if(oAUth2Token != null){

            String accessToken = oAUth2Token.getOauth2_token();

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            HttpEntity<String> entity = new HttpEntity<>(null, headers);

            if(provider.equals("kakao")){
                log.info("kakao logout start");
                ResponseEntity<String> res = restTemplate.exchange(
                        "https://kapi.kakao.com/v1/user/logout", HttpMethod.POST, entity, String.class);
                log.info("kakao logout : {}", res);
            } else if (provider.equals("google")) {
                log.info("google logout start");
                // Google API 서버로 로그아웃 요청 보내기
                ResponseEntity<String> res = restTemplate.exchange(
                        "https://www.google.com/accounts/Logout", HttpMethod.POST, entity, String.class);
                log.info("google logout = {}", res);

            }
        }
        refreshTokenService.deleteTokens(account.getMember().getMemail());

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
