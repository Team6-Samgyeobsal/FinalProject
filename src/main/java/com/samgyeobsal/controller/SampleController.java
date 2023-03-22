package com.samgyeobsal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samgyeobsal.domain.order.OrderFormDTO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

@Controller
@RequestMapping("/sample")
@RequiredArgsConstructor
@Slf4j
public class SampleController {
    @GetMapping
    public String sample(){
        return "intro/intro_main";
    }


}
