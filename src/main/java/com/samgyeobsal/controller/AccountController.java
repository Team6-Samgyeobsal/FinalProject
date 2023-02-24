package com.samgyeobsal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/account")
public class AccountController {

    @GetMapping("/login")
    public String loginPage(){
        return "account/login";
    }

    @GetMapping("/registIntro")
    public String registIntroPage(){
        return "account/regist_intro";
    }

    @GetMapping("/registComplete")
    public String registCompletePage(){
        return "account/regist_complete";
    }
}
