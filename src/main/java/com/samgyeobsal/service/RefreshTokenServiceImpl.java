package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.mapper.RefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public void insertRefreshToken(RefreshTokenVO refreshToken) {
        RefreshTokenVO findToken = refreshTokenMapper.findRefTokenByEmail(refreshToken.getMemail());
        // 이미 존재한다면
        refreshTokenMapper.insertRefToken(refreshToken);
    }

    @Override
    public RefreshTokenVO findRefTokenByToken(String email) {
        return refreshTokenMapper.findRefTokenByToken(email);
    }

    @Override
    public void insertOAuth2Token(OAuth2TokenVO token) {
        refreshTokenMapper.insertOAuth2Token(token);
    }

    @Override
    public OAuth2TokenVO getOAuth2TokenByEmail(String memail) {
        return refreshTokenMapper.getOAuth2TokenByEmail(memail);
    }

    @Override
    @Transactional
    public void deleteTokens(String memail) {
        // refreshToken 과 oauth2Token 삭제
        refreshTokenMapper.deleteRefreshToken(memail);
        refreshTokenMapper.deleteOAuth2Token(memail);
    }
}
