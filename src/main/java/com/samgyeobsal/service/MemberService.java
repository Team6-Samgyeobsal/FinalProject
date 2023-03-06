package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.InsertFormMemberDTO;
import com.samgyeobsal.domain.member.LoginDTO;

public interface MemberService {

    boolean isExist(String email);

    void insertMember(InsertFormMemberDTO member);

    void login(LoginDTO loginDTO);
}
