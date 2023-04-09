package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class RefreshTokenMapperTest {

    @Autowired
    private RefreshTokenMapper refreshTokenMapper;
    @Autowired
    private OAuth2TokenMapper oAuth2TokenMapper;

    @Test
    @Transactional
    void insertRefToken(){
        RefreshTokenVO tokenVO = new RefreshTokenVO();
        tokenVO.setMemail("user@gmail.com");
        tokenVO.setRef_token("testRefToken");
        refreshTokenMapper.insertRefToken(tokenVO);
    }

    @Test
    @Transactional
    void findRefTokenByRefToken(){
        String token = "testRefToken";
        String email = "testEmail";
        RefreshTokenVO tokenVO = new RefreshTokenVO();
        tokenVO.setMemail(email);
        tokenVO.setRef_token("testRefToken");
        refreshTokenMapper.insertRefToken(tokenVO);

        RefreshTokenVO findToken = refreshTokenMapper.findRefTokenByEmail(token);
        Assertions.assertEquals(findToken.getMemail(), email);
    }

    @Test
    void insertOAuth2Token() {
        OAuth2TokenVO token = new OAuth2TokenVO();
        token.setOauth2_token("123123");
        token.setMemail("user@gmail.com");
        oAuth2TokenMapper.insertOAuth2Token(token);

        OAuth2TokenVO token2 = new OAuth2TokenVO();
        token2.setOauth2_token("234234");
        token2.setMemail("user@gmail.com");
        oAuth2TokenMapper.insertOAuth2Token(token2);

        OAuth2TokenVO tokenVO = oAuth2TokenMapper.getOAuth2TokenByEmail("user@gmail.com");
        log.info("tokenVO = {}", tokenVO);

    }
}
