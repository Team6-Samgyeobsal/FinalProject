package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.InsertFormMemberDTO;

public interface MemberService {

    boolean isExist(String email);

    void insertMember(InsertFormMemberDTO member);
}
