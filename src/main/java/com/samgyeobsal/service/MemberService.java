package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.InsertFormMemberDTO;
import com.samgyeobsal.domain.member.LoginDTO;
import com.samgyeobsal.domain.member.MemberVO;

public interface MemberService {

    boolean isExist(String email);

    void insertMember(InsertFormMemberDTO member);

    MemberVO login(LoginDTO loginDTO);


}
