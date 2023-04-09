package com.samgyeobsal.security.domain;

import com.samgyeobsal.domain.member.MemberVO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "SpringSecurityContext에 저장된 회원 객체")
public class Account extends User implements OAuth2User {

    @Schema(description = "등록된 회원 정보")
    private MemberVO member;

    @Schema(title = "OAuth2User 한정 properties")
    private Map<String, Object> OA2_attr;


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
