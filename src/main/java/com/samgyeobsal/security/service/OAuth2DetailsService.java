package com.samgyeobsal.security.service;

import com.samgyeobsal.domain.member.MemberVO;
import com.samgyeobsal.domain.member.OAuth2TokenVO;
import com.samgyeobsal.mapper.MemberMapper;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.security.domain.GoogleUserInfo;
import com.samgyeobsal.security.domain.KakaoUserInfo;
import com.samgyeobsal.security.domain.Oauth2UserInfo;
import com.samgyeobsal.service.MemberService;
import com.samgyeobsal.service.RefreshTokenService;
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
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        log.info("accessTokenType = {}", accessToken.getTokenType().getValue());
        log.info("accessTokenValue = {}", accessToken.getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Oauth2UserInfo oauth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId();

        log.info("oauth2 login provider = {}", provider);

        if (provider.equals("google")) {
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (provider.equals("kakao")) {
            oauth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        log.info("oAUth2User = {}", oAuth2User.getAttributes());

//        String providerId = oauth2UserInfo.getProviderId();
        String username = oauth2UserInfo.getName();

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = "{noop}password" + uuid;

        String email = oauth2UserInfo.getEmail();

        MemberVO member = memberMapper.findMemberByEmail(email, provider);
        if(member == null){
            MemberVO temp = new MemberVO();
            temp.setMemail(email);
            temp.setMname(username);
            temp.setMloginType(provider);
            temp.setMphone("01011111111");
            temp.setMrole(Role.ROLE_USER);
            temp.setMpassword(password);
            memberMapper.insertMember(temp);

            member = memberMapper.findMemberByEmail(email, provider);
        }


        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getMrole().toString()));
        Account account = new Account(member, authorities, oAuth2User.getAttributes());

        OAuth2TokenVO token = new OAuth2TokenVO();
        token.setMemail(email);
        token.setOauth2_token(accessToken.getTokenValue());
            // TODO : 로그아웃 시 db 에서 토큰 가져오기
        refreshTokenService.insertOAuth2Token(token);

        return account;
    }

}
