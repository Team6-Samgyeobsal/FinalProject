package com.samgyeobsal.api;

import com.lilittlecat.chatgpt.offical.ChatGPT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/chatGpt")
@Tag(name = "ChatGPT API")
@RequiredArgsConstructor
@Log4j2
public class ChatGptApi {

    @Value("${chatgpt.client.key}")
    private String chatGPTKey;

    @Operation(summary = "chatGPT에게 질문", description = "chatGPT에게 넘어온 질문을 전달합니다.")
    @Parameter(name = "question", description = "질문")
    @GetMapping
    public ResponseEntity<?> chapGpt(String question) throws IOException {

        OkHttpClient http = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        ChatGPT chatGPT = new ChatGPT(chatGPTKey, http);
        String hello = chatGPT.ask(question);

        return new ResponseEntity<>(hello, HttpStatus.OK);
    }
}
