package com.samgyeobsal.service;

import com.samgyeobsal.domain.notice.NoticeVO;
import com.samgyeobsal.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{


    private final NoticeMapper noticeMapper;

    /**
     * 공지사항 리스트를 리턴
     * @param status : 공지사항 상태(공지/이벤트)
     */
    @Override
    public List<NoticeVO> getNoticeList(String status) {
        return noticeMapper.getNoticeList(status);
    }

    /**
     * 공지사항 상세 정보를 리턴
     * @param nid : 공지사항 아이디
     */
    @Override
    public NoticeVO getNoticeDetail(String nid) {
        return noticeMapper.getNoticeDetail(nid);
    }

    /**
     * 이벤트 상세 정보를 리턴
     * @param eid : 이벤트 아이디
     */
    @Override
    public NoticeVO getEventDetail(String eid) {
        return noticeMapper.getEventDetail(eid);
    }

}
