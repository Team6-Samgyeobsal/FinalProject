package com.samgyeobsal.domain.member;

import com.samgyeobsal.type.LoginType;
import com.samgyeobsal.type.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class InsertFormMemberDTO {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    public MemberVO toMember(){
        MemberVO member = new MemberVO();
        member.setName(name);
        member.setEmail(email);
        member.setPassword(password);
        member.setType(LoginType.LOGIN_FORM);
        member.setRole(Role.ROLE_USER);
        return member;
    }
}
