package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.domain.member.RefreshTokenVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefreshTokenMapper {

    void insertRefToken(@Param("ref_token") RefreshTokenVO refToken);
    RefreshTokenVO findRefTokenByToken(String token);
    RefreshTokenVO findRefTokenByEmail(String email);

    int insertOAuth2Token(OAuth2TokenVO token);

    OAuth2TokenVO getOAuth2TokenByEmail(String memail);

    void deleteRefreshToken(String memail);

    void deleteOAuth2Token(String memail);
}
