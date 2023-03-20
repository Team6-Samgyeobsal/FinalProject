package com.samgyeobsal.controller;

import com.samgyeobsal.mapper.FameMapper;
import com.samgyeobsal.service.FameService;
import com.samgyeobsal.service.FundingService;
import com.samgyeobsal.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/fame")
public class FameController {

    @Autowired
    FameService fameService;


    @GetMapping("")
    public String fame(String type){

        return "fame/fame";
    }


}
