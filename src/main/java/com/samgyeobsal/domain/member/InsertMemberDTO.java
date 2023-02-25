package com.samgyeobsal.domain.member;

import lombok.Data;

@Data
public class InsertMemberDTO {

    private String email;
    private String name;
    private String password;
}
