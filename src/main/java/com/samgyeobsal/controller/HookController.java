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

    // 주문 서비스를 의존선 주입 받음
    private final OrderService orderService;

    // TossHook 객체를 전달받아 처리하는 POST 요청 핸들러 메서드
    @PostMapping("/order")
    public String orderMessage(@RequestBody TossHook tossHook) {

        System.out.println("tosshook : " + tossHook);

        // tossHook 객체의 주문 상태가 "DONE"일 경우 "주문완료"로 변경하고, 그 외에는 "주문실패"
        if(tossHook.getStatus().equals("DONE")) {
            tossHook.setStatus("주문완료");
        } else {
            tossHook.setStatus("주문실패");
        }

        // OrderService를 사용하여 tossHook 객체의 주문 상태를 업데이트
        orderService.updateOrder(tossHook.getStatus(), tossHook.getOrderId());

        return "성공";
    }
}
