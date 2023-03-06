package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.RefreshTokenVO;

public interface RefreshTokenService {

    void insertRefreshToken(RefreshTokenVO refreshToken);

    RefreshTokenVO findRefTokenByToken(String email);

}
