package com.samgyeobsal.controller;

import com.samgyeobsal.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    @Autowired
    NoticeService noticeService;
    @GetMapping("/notice")
    public String notice(){
        return "notice/notice";
    }
    @GetMapping("/notice/{nid}")
    public String noticeDetail(@PathVariable String nid, Model model){
        model.addAttribute("notice",noticeService.getNoticeDetail(nid));
        return "notice/noticeDetail";
    }

    @GetMapping("/event/{eid}")
    public String eventDetail(@PathVariable String eid, Model model){
        model.addAttribute("notice",noticeService.getEventDetail(eid));
        return "notice/noticeDetail";
    }
}
