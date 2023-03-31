package com.samgyeobsal.service;

import com.samgyeobsal.domain.queue.QueueVO;

import java.util.List;

public interface QueueService {

    List<QueueVO> getQueueList(String fid);

    void useQrCode(String qid);

    void insertQueue(String qid);
}
