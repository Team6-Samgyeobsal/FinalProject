package com.samgyeobsal.api;

import com.samgyeobsal.domain.TranslateVO;
import com.samgyeobsal.service.NaverTransService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@Log4j2
@RequestMapping("/api")
public class TranslateApi {

    @Autowired
    private NaverTransService naverTransService;

    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    public @ResponseBody String translate(@RequestBody TranslateVO translateVO) {
        try {
            log.info(translateVO.getSource());
            String result = naverTransService.getTransSentence(translateVO.getText(),translateVO.getSource(), translateVO.getTarget());
            return result;

        } catch (IOException e) {
            throw new RuntimeException("번역 실패", e);
        }
    }
}
