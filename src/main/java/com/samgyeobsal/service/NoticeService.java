package com.samgyeobsal.service;

import com.samgyeobsal.domain.notice.NoticeVO;

import java.util.List;

public interface NoticeService {

    List<NoticeVO> getNoticeList(String status);

    NoticeVO getNoticeDetail(String nid);
    NoticeVO getEventDetail(String eid);
}
