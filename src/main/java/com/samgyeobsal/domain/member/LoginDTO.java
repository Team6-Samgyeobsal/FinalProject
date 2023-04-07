package com.samgyeobsal.domain.member;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "로그인 정보")
public class LoginDTO {
    @Schema(required = true, title = "이메일")
    @Email
    private String email;

    @Schema(required = true, title = "패스워드")
    @NotBlank
    private String password;

}
