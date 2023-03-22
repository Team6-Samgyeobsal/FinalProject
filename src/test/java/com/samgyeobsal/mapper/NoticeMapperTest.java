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
        List<NoticeVO> noticeVO = noticeMapper.getNoticeList();
        noticeVO.forEach(notice -> log.info(notice));
    }
}
