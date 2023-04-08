package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.mapper.OAuth2TokenMapper;
import com.samgyeobsal.mapper.RefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenServiceLocalImpl implements RefreshTokenService {

    private final RefreshTokenMapper refreshTokenMapper;
    private final OAuth2TokenMapper oAuth2TokenMapper;

    @Override
    public void insertRefreshToken(RefreshTokenVO refreshToken) {
        // 이미 존재한다면 update, 아니면 insert
        refreshTokenMapper.insertRefToken(refreshToken);
    }

    @Override
    public RefreshTokenVO findRefTokenByEmail(String email) {
        return refreshTokenMapper.findRefTokenByEmail(email);
    }

    @Override
    public void insertOAuth2Token(OAuth2TokenVO token) {
        oAuth2TokenMapper.insertOAuth2Token(token);
    }

    @Override
    public OAuth2TokenVO getOAuth2TokenByEmail(String memail) {
        return oAuth2TokenMapper.getOAuth2TokenByEmail(memail);
    }

    @Override
    @Transactional
    public void deleteTokens(String memail) {
        // refreshToken 과 oauth2Token 삭제
        refreshTokenMapper.deleteRefreshToken(memail);
        oAuth2TokenMapper.deleteOAuth2Token(memail);
    }
}
