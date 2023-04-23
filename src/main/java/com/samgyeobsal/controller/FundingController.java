package com.samgyeobsal.controller;

import com.samgyeobsal.service.FundingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/web/funding")
public class FundingController {
    @Autowired
    FundingService fundingService;

    @GetMapping
    public String funding(String keyword, Model model){
        model.addAttribute("keyword",keyword);
        return "funding/funding";
    }

    @GetMapping("/{fid}")
    public String fundingDetail(@PathVariable String fid, Model model){
        model.addAttribute("funding",fundingService.getFundingDetail(fid,"FUNDING"));
        return "funding/funding_detail";
    }
}