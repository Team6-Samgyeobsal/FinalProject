package com.samgyeobsal.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RefreshTokenVO {

    private String memail;
    private String ref_token;
}
