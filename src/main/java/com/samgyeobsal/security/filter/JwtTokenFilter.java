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
import org.springframework.util.StringUtils;
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
//        String jwt = resolveToken(request, ACCESS_COOKIE_NAME);
        String token = getTokenFromRequest(request);
        if(token == null){
//            log.info("get accessToken from cookie");
            token = resolveToken(request, ACCESS_COOKIE_NAME);
        }

        // 정상적인 accessToken일 때
        if(token != null && tokenProvider.validateToken(token) == JwtStatus.ACCESS){
//            log.info("get accessToken from header");
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        // accessToken이 없거나 만료 되었을 때 -> refreshToken 확인해야 됨
        }else if(token == null || tokenProvider.validateToken(token) == JwtStatus.EXPIRED){
            String refreshToken = resolveToken(request, REFRESH_COOKIE_NAME);

            // cookie 에 refreshToken이 없을 떄
            if(refreshToken == null) {
//                log.info("not found refreshToken from cookie");
                filterChain.doFilter(request,response);
                return;
            }

            RefreshTokenVO refreshTokenVO = refreshTokenService.findRefTokenByToken(refreshToken);

            // DB에 refreshToken이 없을 경우
            if(refreshTokenVO == null) {
//                log.info("not found refreshToken from DB");
                filterChain.doFilter(request,response);
                return;
            }

            // refresh 토큰이 정상일 경우
            if(tokenProvider.validateToken(refreshToken) == JwtStatus.ACCESS){
                log.info("usable refresh token, issue new accessToken to header");
                String email = tokenProvider.getUserEmail(refreshToken);

                String newAccessToken = tokenProvider.createAccessToken(email);
                SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(newAccessToken));

                Cookie acsCookie = new Cookie("accessToken", newAccessToken);
                acsCookie.setPath("/");
                response.addCookie(acsCookie);

                response.setHeader("Authorization", "Bearer " + newAccessToken);
            }
        }
        filterChain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request, String cookieName){
        String token = readCookie(request, cookieName);
//        log.info("{} token = {}", cookieName, token);
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

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean isAjax(HttpServletRequest req){
        String header = req.getHeader("x-requested-with");
        return "XMLHttpRequest".equals(header);

    }
}
