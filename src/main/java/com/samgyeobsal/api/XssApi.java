package com.samgyeobsal.api;

import com.samgyeobsal.service.XssService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test/v1/xss-test")
public class XssApi {

    private final XssService xssService;

    @PostMapping("/parameter")
    public String strInput(@RequestParam String input){

        return xssService.stringTest(input);
    }
}