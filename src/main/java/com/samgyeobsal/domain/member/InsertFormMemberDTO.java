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

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;

    public MemberVO toMember(){
        MemberVO member = new MemberVO();
        member.setMname(name);
        member.setMemail(email);
        member.setMpassword(password);
        member.setMloginType(LoginType.LOGIN_FORM);
        member.setMrole(Role.ROLE_USER);
        member.setMphone(phone);
        return member;
    }
}
