package com.samgyeobsal.api;

import com.lilittlecat.chatgpt.offical.ChatGPT;
import com.samgyeobsal.service.NaverTransService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/chatGpt")
@RequiredArgsConstructor
@Log4j2
public class ChatGptApi {

    private final NaverTransService naverTransService;

    @GetMapping("")
    public ResponseEntity<?> chapGpt(String question) throws IOException {

//        String result = naverTransService.getTransSentence(question,"ko","en");

        OkHttpClient http = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        ChatGPT chatGPT = new ChatGPT("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", http);
        String hello = chatGPT.ask(question);

//        String result2=naverTransService.getTransSentence(hello,"en","ko");

        return new ResponseEntity<>(hello, HttpStatus.OK);
    }
}
