package com.samgyeobsal.api;

import com.samgyeobsal.domain.queue.QueueVO;
import com.samgyeobsal.service.QueueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value="QueueApi")
@SwaggerDefinition(tags = {@Tag(name = "QueueApi",
description = "모바일 대기열 기능")})
@Log4j2
@RestController
@RequestMapping("/api/queue")
public class QueueApi {

    @Autowired
    QueueService queueService;

    @ApiOperation(value = "get Queuelist",
    notes = "대기열에서 해당 fid에 매핑되는 값을 가져온다. ",
    httpMethod = "SELECT")

    // QRUSEDDATE = null만 출력
    @GetMapping("/list")
    public ResponseEntity<List<QueueVO>> getQueueList(String fid) {
        List<QueueVO> queue = queueService.getQueueList(fid);
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
