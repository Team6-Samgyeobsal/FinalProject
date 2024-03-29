package com.samgyeobsal.security.service;

import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.mapper.MemberMapper;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.type.LoginType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormUserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    /**
     * 회원 이메일로 DB에서 회원 정보를 조회 후, 암호화된 패스워드 비교
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO member = memberMapper.findMemberByEmail(username,null);

        if(member == null) return null;

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getMrole().toString()));
        Account account = new Account(member,authorities);
        return account;
    }

}
