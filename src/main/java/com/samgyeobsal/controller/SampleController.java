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
    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    @Value("${toss.secret.key}")
    private String secretKey;

    @GetMapping
    public String sample(){
        return "intro/intro_main";
    }


    @GetMapping("/testSuccess")
    public String testSuccess(
            @AuthenticationPrincipal Account account,
            @RequestParam("order") String orderFormStr,
            @RequestParam("orderId") String orderId,
            @RequestParam("paymentKey") String paymentKey,
            @RequestParam("amount") int amount) throws JsonProcessingException {
        log.info("orderFormStr = {}", orderFormStr);

        OrderFormDTO orderFormDTO = objectMapper.readValue(orderFormStr, OrderFormDTO.class);
        log.info("orderForm = {}", orderFormDTO);
        String email = account.getMember().getMemail();
        if (sendTossConfirm(paymentKey, orderId, amount)) {
            orderService.saveOrder(orderFormDTO, email);
        }else{
            return "error/error";
        }

        return "redirect:/web/mypage/order/"+orderId;
    }

    private boolean sendTossConfirm(String paymentKey, String orderId, int amount){
        try{

            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(Base64.getEncoder().encodeToString(secretKey.getBytes()));
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject object = new JSONObject();

            object.put("paymentKey", paymentKey);
            object.put("orderId", orderId);
            object.put("amount", amount);

            HttpEntity<String> requestEntity = new HttpEntity<>(object.toString(), headers);

            String url = "https://api.tosspayments.com/v1/payments/confirm";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            log.info("sendTossConfirm response = {}", response.getBody());
            return response.getStatusCode() == HttpStatus.OK;
        }catch (Exception e){
            log.info("error ",e);
            return false;
        }
    }


}
