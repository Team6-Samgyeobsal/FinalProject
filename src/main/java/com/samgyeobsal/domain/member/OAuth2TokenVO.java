package com.samgyeobsal.domain.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class OAuth2TokenVO {
    private String memail;
    private String oauth2_token;
}
