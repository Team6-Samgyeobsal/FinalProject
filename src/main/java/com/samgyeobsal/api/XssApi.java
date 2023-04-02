package com.samgyeobsal.api;

import com.samgyeobsal.service.XssService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @filename XssApi
 * @author 최태승
 * @since 2023.04.02
 * lucy
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.04.02	최태승		최초 생성
 * </pre>
 */
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