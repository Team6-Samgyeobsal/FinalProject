package com.samgyeobsal.domain.member;

import com.samgyeobsal.type.LoginType;
import com.samgyeobsal.type.Role;
import lombok.*;

@Getter
@Setter
@ToString
public class MemberVO {
    private String email;
    private String password;
    private String name;
    private Role role;
    private LoginType type;
}
