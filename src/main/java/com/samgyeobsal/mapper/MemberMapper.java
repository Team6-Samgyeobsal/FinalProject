package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.type.LoginType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    MemberVO findMemberByEmail(@Param("email") String email, @Param("loginType") String type);

    void insertMember(MemberVO member);

    List<FundingVO> findFundingListByEmail(@Param("email") String email);

    FundingVO findActiveStoreByEmail(String email);

    void updateMemberProfile(String memail, String imageUrl);
}
