package com.samgyeobsal.api;

import com.samgyeobsal.domain.fame.FameCriteria;
import com.samgyeobsal.domain.fame.FameVO;
import com.samgyeobsal.service.FameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/fame")
@Tag(name = "명예의전당 API")
@RequiredArgsConstructor
@Log4j2
public class FameApi {

    private final FameService fameService;

    @Operation(summary = "명예의 전당에 올라간 매장 리턴", description = "스토어 중 입점 기간이 지난 매장을 리턴합니다.")
    @GetMapping("/list")
    public ResponseEntity<List<FameVO>> getFameList(FameCriteria criteria) {
        log.info(criteria);
        List<FameVO> list= fameService.getFameList(criteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
