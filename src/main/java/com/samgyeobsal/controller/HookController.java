package com.samgyeobsal.controller;

import com.samgyeobsal.dto.request.TossHook;
import com.samgyeobsal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @filename HookController
 * @author 최태승
 * @since 2023.03.04
 * TossPayments Api
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.04	최태승		최초 생성
 * </pre>
 */
@RestController
@RequestMapping("/web/hook")
@RequiredArgsConstructor
public class HookController {

    private final OrderService orderService;

    @PostMapping("/order")
    public String orderMessage(@RequestBody TossHook tossHook) {

        if(tossHook.getStatus().equals("DONE")) {
            tossHook.setStatus("주문완료");
        } else {
            tossHook.setStatus("주문실페");
        }
        orderService.updateOrder(tossHook.getStatus(), tossHook.getOrderId());

        return "성공";
    }
}
