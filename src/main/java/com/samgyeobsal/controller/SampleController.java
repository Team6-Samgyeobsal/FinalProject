package com.samgyeobsal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleController {

    @GetMapping
    public String sample(){
        return "intro/intro_main";
    }

    /*@GetMapping("/test")
    public String test() {return "sample/test.html"; }*/

    @GetMapping("/testSuccess")
    public String testSuccess() {return "sample/testSuccess.html"; }


}
