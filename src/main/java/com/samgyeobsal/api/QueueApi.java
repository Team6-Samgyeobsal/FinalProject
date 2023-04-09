package com.samgyeobsal.api;

import com.samgyeobsal.domain.queue.QueueVO;
import com.samgyeobsal.service.QueueService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*@Api(value="QueueApi")
@SwaggerDefinition(tags = {@Tag(name = "QueueApi",
description = "모바일 대기열 기능")})*/
@Log4j2
@RestController
@RequestMapping("/api/queue")
public class QueueApi {

    @Autowired
    QueueService queueService;


    // QRUSEDDATE = null만 출력
    @GetMapping("/list")
    public ResponseEntity<List<QueueVO>> getQueueList(String fid) {
        List<QueueVO> queue = queueService.getQueueList(fid);
        return new ResponseEntity<>(queue, HttpStatus.OK);
    }

    // 판매내역 조회
    @GetMapping("/saleslist")
    public ResponseEntity<List<QueueVO>> getSalesList(String fid){
        List<QueueVO> queue = queueService.getSalesList(fid);
        return new ResponseEntity<>(queue, HttpStatus.OK);
    }

    // 리스트에서 대기열 삭제 -> QRUSEDDATE 값을 넣는다
    @PostMapping("/useQrCode")
    public ResponseEntity<String> useQrCode(@RequestBody Map<String, String> map) {
        queueService.useQrCode(map.get("qid"));
        System.out.println("리스트 대기열 삭제 :::::");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PostMapping("/insertQueue")
    public ResponseEntity<String> insertQueue(@RequestBody Map<String, String> map) {
        queueService.insertQueue(map.get("qid"));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
