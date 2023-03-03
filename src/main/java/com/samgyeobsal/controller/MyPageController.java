package com.samgyeobsal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/mypage")
public class MyPageController {

    @GetMapping
    public String mypage(){
        return "mypage/mypage";
    }

    @GetMapping("/maker")
    public String mypageMaker(){
        return "mypage/mypage_maker";
    }

    @GetMapping("/order")
    public String myorderPage(){
        return "mypage/myorder";
    }

    @GetMapping("/order/{orderId}")
    public String myorderDetailPage(@PathVariable("orderId") String orderId){

        return "mypage/myorder_detail";
    }
}
