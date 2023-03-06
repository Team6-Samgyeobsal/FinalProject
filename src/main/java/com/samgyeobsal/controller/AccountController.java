package com.samgyeobsal.controller;

import com.samgyeobsal.domain.member.InsertFormMemberDTO;
import com.samgyeobsal.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/web/account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final MemberService memberService;

    @GetMapping("/oauth2/success")
    public String oauth2Success(@RequestParam("accessToken") String accessToken,
                                @RequestParam("refreshToken") String refreshToken,
                                HttpServletResponse response){
        Cookie acsCookie = new Cookie("accessToken", accessToken);
        acsCookie.setPath("/");
        response.addCookie(acsCookie);

        Cookie refCookie = new Cookie("refreshToken", refreshToken);
        acsCookie.setPath("/");
        response.addCookie(refCookie);
        return "redirect:/web";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, name = "error") String error,
                            Model model){
        model.addAttribute("error", error);
        return "account/login";
    }

    @GetMapping("/registIntro")
    public String registIntroPage(Model model){

        model.addAttribute("member",new InsertFormMemberDTO());
        return "account/regist_intro";
    }

    @PostMapping("/registIntro")
    public String registIntro(
            @Validated @ModelAttribute("member") InsertFormMemberDTO member,
            BindingResult bindingResult, RedirectAttributes redirect){
        if(bindingResult.hasErrors()){
            return "account/regist_intro";
        }

        memberService.insertMember(member);
        redirect.addFlashAttribute("name", member.getName());
        return "redirect:/web/account/registComplete";
    }

    @GetMapping("/registComplete")
    public String registCompletePage(){
        return "account/regist_complete";
    }
}
