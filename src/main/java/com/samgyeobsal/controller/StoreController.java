package com.samgyeobsal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/store")
public class StoreController {

    @GetMapping("")
    public String select(){
        return "store/store";
    }
}
