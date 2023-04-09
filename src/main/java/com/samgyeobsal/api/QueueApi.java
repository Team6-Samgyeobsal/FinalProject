package com.samgyeobsal.api;

import com.samgyeobsal.domain.queue.QueueVO;
import com.samgyeobsal.service.QueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "대기열 API")
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/queue")
public class QueueApi {

    private final QueueService queueService;


    // QRUSEDDATE = null만 출력
    @Operation(summary = "대기열 목록 리턴", description = "해당 매장의 대기열을 리턴합니다.")
    @Parameter(name = "fid", description = "펀딩아이디")
    @GetMapping("/list")
    public ResponseEntity<List<QueueVO>> getQueueList(String fid) {
        List<QueueVO> queue = queueService.getQueueList(fid);
        return new ResponseEntity<>(queue, HttpStatus.OK);
    }

    // 판매내역 조회
    @Operation(summary = "판매내역 목록 리턴", description = "해당 매장의 판매내역을 리턴합니다.")
    @Parameter(name = "fid", description = "펀딩아이디")
    @GetMapping("/saleslist")
    public ResponseEntity<List<QueueVO>> getSalesList(String fid){
        List<QueueVO> queue = queueService.getSalesList(fid);
        return new ResponseEntity<>(queue, HttpStatus.OK);
    }

    // 리스트에서 대기열 삭제 -> QRUSEDDATE 값을 넣는다
    @Operation(summary = "QR코드 사용", description = "사장님이 대기열에서 해당 QR코드 주문을 삭제합니다.")
    @Parameter(name = "qid", description = "QR코드 아이디")
    @PostMapping("/useQrCode")
    public ResponseEntity<String> useQrCode(@RequestBody Map<String, String> map) {
        queueService.useQrCode(map.get("qid"));
        System.out.println("리스트 대기열 삭제 :::::");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Operation(summary = "대기열에 추가", description = "사장님이 QR코드를 읽으면 해당 QR코드 주문을 대기열에 추가합니다.")
    @Parameter(name = "qid", description = "QR코드 아이디")
    @PostMapping("/insertQueue")
    public ResponseEntity<String> insertQueue(@RequestBody Map<String, String> map) {
        queueService.insertQueue(map.get("qid"));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
