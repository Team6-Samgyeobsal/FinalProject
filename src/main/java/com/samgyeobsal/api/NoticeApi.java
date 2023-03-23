package com.samgyeobsal.api;

import com.samgyeobsal.domain.notice.NoticeVO;
import com.samgyeobsal.service.NoticeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api/notice")
public class NoticeApi {
    @Autowired
    NoticeService noticeService;
    @GetMapping("/list")
    public ResponseEntity<List<NoticeVO>> getNoticeList(String status) {
        List<NoticeVO> notice= noticeService.getNoticeList(status);
        log.info("NoticeList = "+notice);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }
}
