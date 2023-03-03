package com.samgyeobsal.controller;

import com.samgyeobsal.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * @filename OrderController
 * @author 최태승
 * @since 2023.02.24
 * Order 페이지 띄우기
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.02.24	최태승		최초 생성
 * </pre>
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/web/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    private String mId = "testuser";


    // 편딩 id로 펀딩 찾아서 해당 리워드들 모델에 넣어줌
    @GetMapping("/{fundingId}/step1")
    public String orderStep1Page(@PathVariable("fundingId") String fundingId){
        return "order/order_step1";
    }

    // step2 페이지로 리다이렉트 하게끔
    @PostMapping("/{fundingId}/step1")
    public String orderStep1(
            @PathVariable("fundingId") String fundingId,
            RedirectAttributes redirect){

//        redirect.addFlashAttribute();
        return "redirect:/web/order/step2";
    }

    @GetMapping("/{fundingId}/step2")
    public String orderStep2Page(){
        return "order/order_step2";
    }

}
