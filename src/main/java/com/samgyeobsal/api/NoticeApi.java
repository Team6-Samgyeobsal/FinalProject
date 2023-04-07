package com.samgyeobsal.api;

import com.samgyeobsal.domain.notice.NoticeVO;
import com.samgyeobsal.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "공지사항 API")
@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/notice")
public class NoticeApi {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 리스트 리턴", description = "조건에 맞는 공지사항들을 리턴합니다.")
    @Parameter(name = "status", description = "종류 (공지 / 이벤트)")
    @GetMapping("/list")
    public ResponseEntity<List<NoticeVO>> getNoticeList(String status) {
        List<NoticeVO> notice= noticeService.getNoticeList(status);
        log.info("NoticeList = "+notice);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }
}
