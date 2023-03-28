package com.samgyeobsal.api;

import com.samgyeobsal.service.MakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageApi {

    private final MakerService makerService;

    @PostMapping("/maker/funding/{fid}/submit")
    public ResponseEntity<String> submitDocument(@PathVariable("fid") String fid) {
        makerService.submitDocument(fid);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
