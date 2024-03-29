package com.samgyeobsal.api;

import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.member.LoginDTO;
import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.security.provider.JwtTokenProvider;
import com.samgyeobsal.security.service.FormUserDetailService;
import com.samgyeobsal.service.MemberService;
import com.samgyeobsal.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
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
@Tag(name = "계정 API")
@RequestMapping("/api/account")
public class AccountApi {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Operation(summary ="이메일 중복확인", description = "회원가입 시 이미 가입된 이메일인지 확인합니다.")
    @Parameter(name = "email", description = "확인할 이메일")
    @GetMapping("/dupCheck")
    public ResponseEntity<Boolean> dupCheck(
            @RequestParam("email") String email){
        boolean isExist = memberService.isExist(email);
        return new ResponseEntity<>(isExist, HttpStatus.OK);
    }

    @Operation(summary = "로그인 수행", description = "JWT 방식의 로그인을 수행하고 토큰을 발급하고, 이전 페이지로 이동합니다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request,
            @Validated  @RequestBody LoginDTO loginDTO,
            BindingResult bindingResult, HttpServletResponse response) {

        String prevPage = (String) request.getSession().getAttribute("prevPage");
        log.info("prevPage = {}", prevPage);

        MemberVO member = memberService.login(loginDTO);

        if(bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);

        FundingVO activeStore = memberService.findActiveStoreByEmail(loginDTO.getEmail());

        HashMap<String, Object> res = new HashMap<>();
        String accessToken = jwtTokenProvider.createAccessToken(loginDTO.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(loginDTO.getEmail());
        member.setMpassword(null);

        res.put("member", member);
        res.put("accessToken", accessToken);
        res.put("refreshToken", refreshToken);
        res.put("store", activeStore);
        res.put("prevPage", prevPage);

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

    @Operation(summary = "로그아웃 수행", description = "accessToken을 무효화하고, refreshToken을 삭제합니다.")
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
