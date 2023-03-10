package com.samgyeobsal.controller;

import com.samgyeobsal.service.FundingService;
import com.samgyeobsal.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @Autowired
    FundingService fundingService;

    @GetMapping("")
    public String store(Model model){
        model.addAttribute("store",storeService.getStoreList());
        return "store/store";
    }
    @GetMapping("/{fid}")
    public String storeDetail(@PathVariable String fid, Model model){

        model.addAttribute("funding",fundingService.getFundingDetail(fid,"STORE"));
        return "funding/product_detail";
    }
}
