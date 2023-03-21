package com.samgyeobsal.api;

import com.samgyeobsal.domain.fame.FameCriteria;
import com.samgyeobsal.domain.fame.FameVO;
import com.samgyeobsal.service.FameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/fame")
@RequiredArgsConstructor
@Log4j2
public class FameApi {

    private final FameService fameService;

    @GetMapping("/list")
    public ResponseEntity<List<FameVO>> getFameList(FameCriteria criteria) {
        log.info(criteria);
        List<FameVO> list= fameService.getFameList(criteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
