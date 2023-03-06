package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.member.RefreshTokenVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RefreshTokenMapperTest {

    @Autowired
    private RefreshTokenMapper refreshTokenMapper;

    @Test
    @Transactional
    void insertRefToken(){
        RefreshTokenVO tokenVO = new RefreshTokenVO();
        tokenVO.setMemail("testEmail");
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

        RefreshTokenVO findToken = refreshTokenMapper.findRefTokenByToken(token);
        Assertions.assertEquals(findToken.getMemail(), email);
    }

    @Test
    @Transactional
    void findRefTokenByEmail(){
        String token = "testRefToken";
        String email = "testEmail";
        RefreshTokenVO tokenVO = new RefreshTokenVO();
        tokenVO.setMemail(email);
        tokenVO.setRef_token("testRefToken");
        refreshTokenMapper.insertRefToken(tokenVO);

        RefreshTokenVO findToken = refreshTokenMapper.findRefTokenByEmail(email);
        Assertions.assertEquals(findToken.getMemail(), email);
    }
}
