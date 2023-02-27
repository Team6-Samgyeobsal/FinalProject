package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.InsertFormMemberDTO;
import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.mapper.MemberMapper;
import com.samgyeobsal.type.LoginType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void insertMember(InsertFormMemberDTO member) {
        String password = member.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);
        memberMapper.insertMember(member.toMember());
    }

    @Override
    public boolean isExist(String email) {
        MemberVO memberVO = null;
        for(LoginType type : LoginType.values()){
            MemberVO tmp = memberMapper.findMemberByEmail(email, type);
            if(tmp != null){
                memberVO = tmp;
                break;
            }
        }
        return memberVO != null;
    }
}
