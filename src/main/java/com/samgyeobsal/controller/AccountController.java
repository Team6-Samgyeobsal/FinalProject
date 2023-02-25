package com.samgyeobsal.controller;

import com.samgyeobsal.domain.member.InsertMemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/account")
@Slf4j
public class AccountController {

    @GetMapping("/login")
    public String loginPage(){
        return "account/login";
    }

    @GetMapping("/registIntro")
    public String registIntroPage(Model model){

        model.addAttribute("member",new InsertMemberDTO());
        return "account/regist_intro";
    }

    @PostMapping("/registIntro")
    public String registIntro(@ModelAttribute("member") InsertMemberDTO member){
        log.info("member = {}", member);


        return "redirect:/registComplete";
    }

    @GetMapping("/registComplete")
    public String registCompletePage(){
        return "account/regist_complete";
    }
}
