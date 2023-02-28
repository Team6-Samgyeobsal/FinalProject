package com.samgyeobsal.security.service;

import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.mapper.MemberMapper;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.type.LoginType;
import com.samgyeobsal.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("OAuth2DetailsService loadUser 시작");

        OAuth2User oAuth2User = super.loadUser(userRequest);
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + " : " + v);
        });

        try{
            MemberVO member = saveOAuth2Member(oAuth2User.getAttributes());

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(member.getMrole().toString()));
            Account account = new Account(member, authorities, oAuth2User.getAttributes());
            return account;
        }catch (Exception e){
            log.error("OAuth2DetailsService loadUser error",e);
            return null;
        }
    }

    private MemberVO saveOAuth2Member(Map<String, Object> attr){
        String email = attr.get("email").toString();
        String name = "USER_"+UUID.randomUUID().toString().substring(7);

        MemberVO member = memberMapper.findMemberByEmail(email, LoginType.LOGIN_FORM);
        if(member != null)
            throw new RuntimeException("already joined by form login");

        member = memberMapper.findMemberByEmail(email, LoginType.LOGIN_GOOGLE);
        if(member != null)
            return member;
        member = new MemberVO();
        member.setMemail(email);
        member.setMname(name);
        member.setMloginType(LoginType.LOGIN_GOOGLE);
        member.setMrole(Role.ROLE_USER);
        member.setMpassword("{noop}1111");

        memberMapper.insertMember(member);
        return memberMapper.findMemberByEmail(email, LoginType.LOGIN_GOOGLE);
    }
}
