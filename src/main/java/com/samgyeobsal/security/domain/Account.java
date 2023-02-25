package com.samgyeobsal.security.domain;

import com.samgyeobsal.domain.member.MemberVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Account extends User {

    private MemberVO member;

    public Account(MemberVO member,
                   Collection<? extends GrantedAuthority> authorities) {
        super(member.getEmail(), member.getPassword(), authorities);
        this.member = member;
    }

}
