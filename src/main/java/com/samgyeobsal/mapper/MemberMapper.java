package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.type.LoginType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    MemberVO findMemberByEmail(@Param("email") String email, @Param("loginType") LoginType type);
}
