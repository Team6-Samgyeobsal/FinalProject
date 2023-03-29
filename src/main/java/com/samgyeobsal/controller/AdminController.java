package com.samgyeobsal.controller;

import com.samgyeobsal.domain.funding.FundingDetailVO;
import com.samgyeobsal.service.AdminService;
import com.samgyeobsal.service.FundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/web/admin")
@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final FundingService fundingService;
    private final AdminService adminService;

    @GetMapping
    public String index() {
        return "admin/index";
    }

    @GetMapping("/document")
    public String fundingDocs(Model model) {
        model.addAttribute("docs", adminService.getDocumentList());
        return "admin/funding_docs";
    }

    @GetMapping("/document/{fid}/preview")
    public String fundingPreview(Model model, @PathVariable("fid") String fid) {
        FundingDetailVO detail = fundingService.getFundingDetail(fid, "PARTICIPATE");
        log.info("detail = {}", detail);

        model.addAttribute("funding", detail);
        return "admin/funding_preview";
    }

    @GetMapping("/funding")
    public String fundingRank(){
        return "admin/funding_rank";
    }

    @GetMapping("/review")
    public String reviewManagement(){
        return "admin/review_manage";
    }
}
