package com.samgyeobsal.security.domain;

import com.samgyeobsal.domain.member.MemberVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class Account extends User implements OAuth2User {

    private MemberVO member;

    private Map<String, Object> OA2_attr;

    private String accessToken;

    private String provider;

    public Account(MemberVO member,
                   Collection<? extends GrantedAuthority> authorities) {
        super(member.getMemail(), member.getMpassword(), authorities);
        this.member = member;
    }
    public Account(MemberVO member,
                   Collection<? extends GrantedAuthority> authorities,
                   Map<String,Object> OA2_attr) {
        super(member.getMemail(), member.getMpassword(), authorities);
        this.OA2_attr = OA2_attr;
        this.member = member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.OA2_attr;
    }

    @Override
    public String getName() {
        return member.getMname();
    }
}
