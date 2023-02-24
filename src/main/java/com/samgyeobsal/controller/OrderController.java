package com.samgyeobsal.controller;

import com.samgyeobsal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
public class OrderController {

    private final OrderService service;

    private String memberName = "isshosng";

    @RequestMapping(value="", method = RequestMethod.GET)
    public String orderForm(Model model){

        return "order/orderDetail";

    }
}
