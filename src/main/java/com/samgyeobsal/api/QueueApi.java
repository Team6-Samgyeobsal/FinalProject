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
    @PostMapping("/useQrCode")
    public ResponseEntity<String> useQrCode(@RequestBody Map<String, String> map) {
        queueService.useQrCode(map.get("qid"));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PostMapping("/insertQueue")
    public ResponseEntity<String> insertQueue(@RequestBody Map<String, String> map) {
        queueService.insertQueue(map.get("qid"));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
