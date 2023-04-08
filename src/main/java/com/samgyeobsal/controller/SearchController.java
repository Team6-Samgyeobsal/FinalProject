package com.samgyeobsal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/web/search")
public class SearchController {

    @GetMapping("")
    public String search(String keyword, Model model){
        model.addAttribute("keyword",keyword);
        return "search/search";
    }
}
