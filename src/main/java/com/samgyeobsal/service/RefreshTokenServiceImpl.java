package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.RefreshTokenVO;
import com.samgyeobsal.mapper.RefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public void insertRefreshToken(RefreshTokenVO refreshToken) {
        RefreshTokenVO findToken = refreshTokenMapper.findRefTokenByEmail(refreshToken.getMemail());
        // 이미 존재한다면
        if(findToken != null){
            refreshTokenMapper.updateRefToken(refreshToken);
            return;
        }
        refreshTokenMapper.insertRefToken(refreshToken);
    }

    @Override
    public RefreshTokenVO findRefTokenByToken(String email) {
        return refreshTokenMapper.findRefTokenByToken(email);
    }

}
