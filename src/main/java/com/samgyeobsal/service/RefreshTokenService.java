package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;

public interface RefreshTokenService {

    void insertRefreshToken(RefreshTokenVO refreshToken);

    RefreshTokenVO findRefTokenByEmail(String email);

    void insertOAuth2Token(OAuth2TokenVO token);

    OAuth2TokenVO getOAuth2TokenByEmail(String memail);

    void deleteTokens(String memail);

}
