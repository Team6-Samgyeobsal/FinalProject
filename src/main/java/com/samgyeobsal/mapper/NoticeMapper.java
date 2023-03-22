package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.notice.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeVO> getNoticeList();
}
