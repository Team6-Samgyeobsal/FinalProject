package com.samgyeobsal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samgyeobsal.domain.order.OrderFormDTO;
import com.samgyeobsal.domain.order.OrderListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sample")
@Slf4j
public class SampleController {
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public String sample(){
        return "intro/intro_main";
    }


    @GetMapping("/testSuccess")
    public String testSuccess(
            @RequestParam("order") String orderFormStr,
            @RequestParam("orderId") String orderId) throws JsonProcessingException {
        OrderFormDTO orderFormDTO = mapper.readValue(orderFormStr, OrderFormDTO.class);
        // TODO : orderFormDTO 만들고, insert 문 작성

        log.info("orderForm = {}", orderFormDTO);

        return "sample/testSuccess";
    }


}
