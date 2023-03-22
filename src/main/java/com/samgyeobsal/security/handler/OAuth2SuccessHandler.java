package com.samgyeobsal.security.handler;

import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.mapper.RefreshTokenMapper;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.security.provider.JwtTokenProvider;
import com.samgyeobsal.security.service.OAuth2DetailsService;
import com.samgyeobsal.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    // 새로운 accessToken과 refreshToken을 발급하고 db에 refreshToken을 저장
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Account account = (Account) authentication.getPrincipal();
        String accessToken = jwtTokenProvider.createAccessToken(account.getMember().getMemail());
        String refreshToken = jwtTokenProvider.createRefreshToken(account.getMember().getMemail());

        Cookie acsCookie = new Cookie("accessToken", accessToken);
        acsCookie.setPath("/");
        response.addCookie(acsCookie);

        Cookie refCookie = new Cookie("refreshToken", refreshToken);
        refCookie.setPath("/");
        response.addCookie(refCookie);

        RefreshTokenVO refreshTokenVO = new RefreshTokenVO();
        refreshTokenVO.setRef_token(refreshToken);
        refreshTokenVO.setMemail(account.getMember().getMemail());
        refreshTokenService.insertRefreshToken(refreshTokenVO);

        getRedirectStrategy().sendRedirect(request, response, "/web/funding");
    }
}
