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

    /**
     * qrused_date가 null이고 대기열에 들어가 있는 주문 정보 리스트 리턴
     * @param fid : 펀딩 아이디
     */
    @Override
    public List<QueueVO> getQueueList(String fid) {
        return queueMapper.getQueueList(fid);
    }

    @Override
    public List<QueueVO> getSalesList(String fid) { return queueMapper.getSalesList(fid); }
    /**
     * qid에 해당하는 qrused_date에 사용 날짜 입력
     * @param qid : QR_CODE 아이디
     */
    @Override
    public void useQrCode(String qid) {
        queueMapper.useQrCode(qid);
    }
    /**
     * qid를 대기열에 입력
     * @param qid : QR_CODE 아이디
     */
    @Override
    public void insertQueue(String qid) {
        int row = queueMapper.insertQueue(qid);
        log.info(qid);
        log.info(row);
    }
}
