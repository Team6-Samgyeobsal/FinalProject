package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.member.RefreshTokenVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefreshTokenMapper {


    void insertRefToken(@Param("ref_token") RefreshTokenVO refToken);
    RefreshTokenVO findRefTokenByToken(String token);
    RefreshTokenVO findRefTokenByEmail(String email);

    void updateRefToken(@Param("ref_token") RefreshTokenVO refToken);
}
