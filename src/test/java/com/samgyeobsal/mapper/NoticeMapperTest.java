package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.notice.NoticeVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
public class NoticeMapperTest {

    @Autowired
    NoticeMapper noticeMapper;

    @Test
    public void getNoticeListTest(){
        String status="notice";
        List<NoticeVO> noticeVO = noticeMapper.getNoticeList(status);
        noticeVO.forEach(notice -> log.info(notice));
    }

    @Test
    public void getNoticeDetailList(){
        String nid ="1";
        NoticeVO noticeVO= noticeMapper.getNoticeDetail(nid);
        log.info("notice= "+noticeVO);
    }
}
