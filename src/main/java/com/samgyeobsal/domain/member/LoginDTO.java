package com.samgyeobsal.domain.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    @Email
    private String email;
    @NotBlank
    private String password;
}
