package com.samgyeobsal.security.filter;

import com.samgyeobsal.security.provider.JwtTokenProvider;
import com.samgyeobsal.security.service.FormUserDetailService;
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

    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request, ACCESS_COOKIE_NAME);
        if(jwt != null && tokenProvider.validateToken(jwt) == JwtStatus.ACCESS){
            Authentication authentication = tokenProvider.getUserEmail(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("token found and set context");
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
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
