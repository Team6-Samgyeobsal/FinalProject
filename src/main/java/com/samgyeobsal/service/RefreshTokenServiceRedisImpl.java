package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.mapper.OAuth2TokenMapper;
import com.samgyeobsal.mapper.RefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("prod")
@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenServiceRedisImpl implements RefreshTokenService {

    private final RedisTemplate<String, String> redisTemplate;
    private final OAuth2TokenMapper oAuth2TokenMapper;

    @Value("${jwt.refresh-token-validity-in-sec}")
    private Long REFRESH_DURATION;

    /**
     * 해당 이메일의 refreshToken Redis 저장
     */
    @Override
    public void insertRefreshToken(RefreshTokenVO refreshToken) {
        redisTemplate.opsForValue().set(refreshToken.getMemail(), refreshToken.getRef_token(),REFRESH_DURATION);
    }

    /**
     * 해당 이메일의 refreshToken 리턴
     * @param email : 회원 이메일
     */
    @Override
    public RefreshTokenVO findRefTokenByEmail(String email) {
        String token = redisTemplate.opsForValue().get(email);
        RefreshTokenVO tokenVO = new RefreshTokenVO();
        tokenVO.setRef_token(token);
        tokenVO.setMemail(email);

        return tokenVO;
    }

    /**
     * 해당 이메일의 oAuth2Token DB 저장
     * @param token
     */
    @Override
    public void insertOAuth2Token(OAuth2TokenVO token) {
        oAuth2TokenMapper.insertOAuth2Token(token);
    }

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
        redisTemplate.delete(memail);
        oAuth2TokenMapper.deleteOAuth2Token(memail);
    }
}
