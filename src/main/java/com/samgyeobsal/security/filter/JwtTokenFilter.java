package com.samgyeobsal.security.filter;

import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.mapper.RefreshTokenMapper;
import com.samgyeobsal.security.provider.JwtTokenProvider;
import com.samgyeobsal.service.RefreshTokenService;
import com.samgyeobsal.type.JwtStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String ACCESS_COOKIE_NAME = "accessToken";
    private static final String REFRESH_COOKIE_NAME = "refreshToken";

    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request, ACCESS_COOKIE_NAME);
        // 정상적인 accessToken일 때
        if(jwt != null && tokenProvider.validateToken(jwt) == JwtStatus.ACCESS){
            log.info("doFilterInternal accessToken 정상");
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("token found and set context");

        // accessToken이 만료되거나 없고 refreshToken이 정상일 때
        }else if(jwt == null || tokenProvider.validateToken(jwt) == JwtStatus.EXPIRED){
            RefreshTokenVO refreshTokenVO = refreshTokenService.findRefTokenByToken(resolveToken(request, REFRESH_COOKIE_NAME));
            String refresh = null;
            if(refreshTokenVO != null) refresh = refreshTokenVO.getRef_token();
            log.info("doFilterInternal accessToken 비정상 && refreshToken 정상");
            if(refresh != null && tokenProvider.validateToken(refresh) == JwtStatus.ACCESS){
                String email = tokenProvider.getUserEmail(refresh);

                String newAccessToken = tokenProvider.createAccessToken(email);
                SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(newAccessToken));

                Cookie acsCookie = new Cookie("accessToken", newAccessToken);
                acsCookie.setPath("/");
                response.addCookie(acsCookie);
            }
        }
        filterChain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request, String cookieName){
        String token = readCookie(request, cookieName);

        if(token != null){
            return token;
        }
        return null;
    }

    private String readCookie(HttpServletRequest request,String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }

    private boolean isAjax(HttpServletRequest req){
        String header = req.getHeader("x-requested-with");
        return "XMLHttpRequest".equals(header);

    }
}
