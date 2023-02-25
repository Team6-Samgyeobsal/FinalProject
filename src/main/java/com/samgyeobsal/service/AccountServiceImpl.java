package com.samgyeobsal.service;

import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.mapper.MemberMapper;
import com.samgyeobsal.type.LoginType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final MemberMapper memberMapper;

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
