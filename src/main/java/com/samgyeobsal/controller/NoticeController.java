package com.samgyeobsal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {
    @GetMapping("")
    public String notice(){
        return "notice/notice";
    }

    @GetMapping("/detail")
    public String noticeDetail(){
        return "notice/noticeDetail";
    }
}
