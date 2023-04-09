package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.member.OAuth2TokenVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuth2TokenMapper {

    int insertOAuth2Token(OAuth2TokenVO token);

    OAuth2TokenVO getOAuth2TokenByEmail(String memail);


    void deleteOAuth2Token(String memail);
}
