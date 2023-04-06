package com.samgyeobsal.service;

import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.member.InsertFormMemberDTO;
import com.samgyeobsal.domain.member.LoginDTO;
import com.samgyeobsal.domain.member.MemberVO;

import java.util.List;

public interface MemberService {

    boolean isExist(String email);

    void insertMember(InsertFormMemberDTO member);

    MemberVO login(LoginDTO loginDTO);

    List<FundingVO> findFindingListByEmail(String email);

    FundingVO findActiveStoreByEmail(String email);


    void updateMemberProfile(String memail, String imageURL);
}
