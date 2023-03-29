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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("oAUth2User = {}", oAuth2User.getAttributes());


        MemberVO member = saveOAuth2Member(userRequest, oAuth2User);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getMrole().toString()));
        Account account = new Account(member, authorities, oAuth2User.getAttributes());

        return account;
    }

    private MemberVO saveOAuth2Member(OAuth2UserRequest userRequest, OAuth2User oAuth2User){

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + providerId;

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = "{noop}passwrd" + uuid;

        String email = oAuth2User.getAttribute("email");


        MemberVO member = memberMapper.findMemberByEmail(email, "email");

        if(member != null)
            throw new RuntimeException("already joined by form login");

        member = memberMapper.findMemberByEmail(email, provider);

        if(member != null) return member;
        member = new MemberVO();
        member.setMemail(email);
        member.setMname(username);
        member.setMloginType("google");
        member.setMphone("01011111111");
        member.setMrole(Role.ROLE_USER);
        member.setMpassword(password);

        memberMapper.insertMember(member);
        return memberMapper.findMemberByEmail(email, provider);
    }
}
