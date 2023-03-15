package com.samgyeobsal.service;

import com.lilittlecat.chatgpt.offical.ChatGPT;
import okhttp3.OkHttpClient;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ChatGptService {
    public static void main(String[] args) {
        OkHttpClient http = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        ChatGPT chatGPT = new ChatGPT("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", http);
        String exitPhrase = "exit";
        while (true){
            Scanner sc = new Scanner(System.in);
            String question=sc.nextLine();
//            if(question.equals(exitPhrase)){
//                break;
//            }
            String answer = chatGPT.ask(question);
            System.out.println(answer);
        }
    }
}