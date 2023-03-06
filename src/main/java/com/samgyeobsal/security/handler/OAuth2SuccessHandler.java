package com.samgyeobsal.security.handler;

import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.security.provider.JwtTokenProvider;
import com.samgyeobsal.security.service.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Account account = (Account) authentication.getPrincipal();
        String accessToken = jwtTokenProvider.createAccessToken(account.getMember().getMemail());
        String refreshToken = jwtTokenProvider.createRefreshToken(account.getMember().getMemail());

        String targetUrl = UriComponentsBuilder.fromUriString("/web/account/oauth2/success")
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
