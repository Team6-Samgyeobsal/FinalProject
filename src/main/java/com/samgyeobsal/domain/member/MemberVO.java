package com.samgyeobsal.domain.member;

import com.samgyeobsal.type.LoginType;
import com.samgyeobsal.type.Role;
import lombok.*;

@Getter
@Setter
@ToString
public class MemberVO {
    private String memail;
    private String mpassword;
    private String mname;
    private Role mrole;
    private String mloginType;
    private String mprofile;

    private String mphone;
    private int mmileage;
}
