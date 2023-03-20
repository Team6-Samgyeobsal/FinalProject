package com.samgyeobsal.controller;

import com.samgyeobsal.domain.event.Event;
import com.samgyeobsal.domain.order.OrderStep1DTO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.mapper.EventMapper;
import com.samgyeobsal.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


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

    @Value("${toss.client.key}")
    private String tossKey;

    private final OrderService orderService;
    private final EventMapper eventMapper;

    private String mId = "testuser";

    /**
     *
     * @param
     * @return
     */


    // 편딩 id로 펀딩 찾아서 해당 리워드들 모델에 넣어줌
    @GetMapping("/{fid}/step1")
    public String orderStep1Page(@PathVariable("fid") String fid,Model model){
        model.addAttribute("products",orderService.getProductList(fid));
        return "order/order_step1";
    }

    // step2 페이지로 리다이렉트 하게끔
    @PostMapping("/{fid}/step1")
    public String orderStep1(@PathVariable("fid") String fid,
                             HttpSession session,
                             @ModelAttribute OrderStep1DTO orderStep1DTO){
        session.setAttribute("order", orderStep1DTO);
        return "redirect:/web/order/"+fid+"/step2";
    }

    @GetMapping("/{fundingId}/step2")
    public String orderStep2Page(Model model, HttpSession session,@PathVariable String fundingId, @AuthenticationPrincipal Account account){
        OrderStep1DTO orderStep1DTO = (OrderStep1DTO) session.getAttribute("order");
        model.addAttribute("order", orderService.getOrderList(orderStep1DTO,fundingId));
        model.addAttribute("tossKey", tossKey);
        System.out.println("orderService.getOrderList(orderStep1DTO)"+orderService.getOrderList(orderStep1DTO,fundingId));
        return "order/order_step2";
    }


    @GetMapping("/coupon")
    public Event checkCouponName(@RequestParam("couponname") String couponName){
        Event event = eventMapper.findByName(couponName);
        System.out.println("쿠폰 :" + event);
        return event;
    }

}
