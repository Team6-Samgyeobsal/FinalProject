package com.samgyeobsal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/web/admin")
@Controller
public class AdminController {

    @GetMapping
    public String index(){
        return "admin/index";
    }

    @GetMapping("/funding")
    public String fundingDocs(){
        return "admin/funding_docs";
    }
}
