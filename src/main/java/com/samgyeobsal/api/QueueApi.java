package com.samgyeobsal.api;

import com.samgyeobsal.domain.funding.FundingCriteria;
import com.samgyeobsal.domain.funding.FundingVO;
import com.samgyeobsal.domain.queue.QueueVO;
import com.samgyeobsal.service.QueueService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/queue")
public class QueueApi {

    @Autowired
    QueueService queueService;

    @GetMapping("/list")
    public ResponseEntity<List<QueueVO>> getQueueList(String fid) {
        List<QueueVO> queue = queueService.getQueueList(fid);
        return new ResponseEntity<>(queue, HttpStatus.OK);
    }
}
