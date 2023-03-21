package com.samgyeobsal.api;

import com.lilittlecat.chatgpt.offical.ChatGPT;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/chatGpt")
@RequiredArgsConstructor
@Log4j2
public class ChatGptApi {
    @GetMapping("")
    public ResponseEntity<?> chapGpt(String question){
        OkHttpClient http = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        ChatGPT chatGPT = new ChatGPT("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", http);
        String hello = chatGPT.ask(question);

        return new ResponseEntity<>(hello, HttpStatus.OK);
    }
}
