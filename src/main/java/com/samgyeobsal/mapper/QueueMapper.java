package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.queue.QueueVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QueueMapper {

    List<QueueVO> getQueueList(String fid);

    List<QueueVO> getSalesList(String fid);

    int useQrCode(String qid);

    int insertQueue(String qid);

}
