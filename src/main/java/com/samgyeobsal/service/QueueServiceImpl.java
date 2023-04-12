package com.samgyeobsal.service;

import com.samgyeobsal.domain.queue.QueueVO;
import com.samgyeobsal.mapper.QueueMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService{

    private final QueueMapper queueMapper;
    @Override
    public List<QueueVO> getQueueList(String fid) {
        return queueMapper.getQueueList(fid);
    }

    @Override
    public List<QueueVO> getSalesList(String fid) { return queueMapper.getSalesList(fid); }

    @Override
    public void useQrCode(String qid) {
        queueMapper.useQrCode(qid);
    }

    @Override
    public void insertQueue(String qid) {
        log.info(qid);
        log.info(queueMapper.insertQueue(qid));
    }
}
