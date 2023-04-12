package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.mapper.OAuth2TokenMapper;
import com.samgyeobsal.mapper.RefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("default")
@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenServiceLocalImpl implements RefreshTokenService {

    private final RefreshTokenMapper refreshTokenMapper;
    private final OAuth2TokenMapper oAuth2TokenMapper;

    /**
     * 해당 이메일의 refreshToken DB 저장
     */
    @Override
    public void insertRefreshToken(RefreshTokenVO refreshToken) {
        // 이미 존재한다면 update, 아니면 insert
        refreshTokenMapper.insertRefToken(refreshToken);
    }

    /**
     * 해당 이메일의 refreshToken 리턴
     * @param email : 회원 이메일
     */
    @Override
    public RefreshTokenVO findRefTokenByEmail(String email) {
        return refreshTokenMapper.findRefTokenByEmail(email);
    }

    /**
     * 해당 이메일의 oAuth2Token DB 저장
     * @param token
     */
    @Override
    public void insertOAuth2Token(OAuth2TokenVO token) {
        oAuth2TokenMapper.insertOAuth2Token(token);
    }

    /**
     * 해당 이메일의 oAuth2Token 조회
     * @param memail : 회원 이메일
     * @return
     */
    @Override
    public OAuth2TokenVO getOAuth2TokenByEmail(String memail) {
        return oAuth2TokenMapper.getOAuth2TokenByEmail(memail);
    }

    /**
     * 해당 이메일의 refreshToken, oAuth2Token 삭제
     * @param memail : 회원 이메일
     */
    @Override
    @Transactional
    public void deleteTokens(String memail) {
        // refreshToken 과 oauth2Token 삭제
        refreshTokenMapper.deleteRefreshToken(memail);
        oAuth2TokenMapper.deleteOAuth2Token(memail);
    }
}
