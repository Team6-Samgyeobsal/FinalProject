package com.samgyeobsal.service;

import com.samgyeobsal.domain.notice.NoticeVO;
import com.samgyeobsal.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    NoticeMapper noticeMapper;
    @Override
    public List<NoticeVO> getNoticeList(String status) {
        return noticeMapper.getNoticeList(status);
    }

    @Override
    public NoticeVO getNoticeDetail(String nid) {
        return noticeMapper.getNoticeDetail(nid);
    }

    @Override
    public NoticeVO getEventDetail(String eid) {
        return noticeMapper.getEventDetail(eid);
    }

}
