package com.samgyeobsal.api;

import com.samgyeobsal.domain.order.OrderRequest;
import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.dto.request.TossHook;
import com.samgyeobsal.mapper.QrCodeMapper;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @filename OrderApi
 * @author 최태승
 * @since 2023.02.24
 * Order Restful API
 * KaKapPay API
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.02.23	최태승		최초 생성
 * </pre>
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Slf4j
public class OrderApi {

    private final OrderService orderService;
    private final QrCodeMapper qrCodeMapper;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderVO> getOrderInfo(@PathVariable("orderId") String orderId){
        OrderVO order = orderService.getOrderByOrderId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{orderId}/qrcode")
    public ResponseEntity<String> getOrderQrCode(@PathVariable("orderId") String orderId){
        log.info("getOrderQrCode orderId = {}", orderId);

        String qrCodeString = qrCodeMapper.getQrCodeString(orderId);
        log.info("qrCodeString = {}", qrCodeString);

        return new ResponseEntity<>(qrCodeString, HttpStatus.OK);
    }

    @PostMapping("/hook")
    public ResponseEntity<String> orderMessage(@RequestBody TossHook tossHook){
        if(tossHook.getStatus().equals("DONE")) {
            tossHook.setStatus("COMPLETE");
        } else {
            tossHook.setStatus("FAILURE");
        }

        orderService.updateOrder(tossHook.getStatus(), tossHook.getOrderId());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
