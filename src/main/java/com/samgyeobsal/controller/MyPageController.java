package com.samgyeobsal.controller;

import com.samgyeobsal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    @GetMapping
    public String mypage(){
        return "mypage/mypage";
    }

    @GetMapping("/order")
    public String myorderPage(){
        return "mypage/myorder";
    }

    @GetMapping("/order/{orderId}")
    public String myorderDetailPage(@PathVariable("orderId") String orderId){
        return "mypage/myorder_detail";
    }

    @GetMapping("/maker")
    public String mypageMaker(){
        return "mypage/mypage_maker";
    }

    @GetMapping("/maker/funding/{fundingId}")
    public String fundingPage(@PathVariable("fundingId") String fundingId) {

        return "mypage/funding_maker";
    }

    @GetMapping("/maker/funding/{fundingId}/baseInfo")
    public String fundingBaseInfo(@PathVariable("fundingId") String fundingId, Model model){

        return "mypage/funding_baseinfo";
    }

    @GetMapping("/maker/funding/{fundingId}/story")
    public String fundingStory(@PathVariable("fundingId") String fundingId){
        return "mypage/funding_story";
    }

    @GetMapping("/maker/funding/{fundingId}/reward")
    public String fundingReward(@PathVariable("fundingId") String fundingId){
        return "mypage/funding_reward";
    }
}
