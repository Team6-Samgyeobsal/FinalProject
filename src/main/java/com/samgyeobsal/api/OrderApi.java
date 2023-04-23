package com.samgyeobsal.api;

import com.samgyeobsal.domain.order.OrderVO;
import com.samgyeobsal.domain.order.TossHook;
import com.samgyeobsal.mapper.QrCodeMapper;
import com.samgyeobsal.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "주문 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Slf4j
public class OrderApi {

    private final OrderService orderService;
    private final QrCodeMapper qrCodeMapper;


    @Operation(summary = "특정 주문의 상세 정보 리턴", description = "주문 아이디에 따라 주문의 상세한 정보를 리턴합니다.")
    @Parameter(name = "orderId", description = "주문아이디")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderVO> getOrderInfo(@PathVariable("orderId") String orderId){
        OrderVO order = orderService.getOrderByOrderId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Operation(summary = "특정 주문 취소", description = "주문 아이디에 따라 해당 주문을 취소합니다.")
    @Parameter(name = "orderId", description = "주문아이디")
    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") String orderId){
        orderService.updateOrder("CANCEL", orderId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Operation(summary = "특정 주문의 QR코드 리턴")
    @Parameter(name = "orderId", description = "주문아이디")
    @GetMapping("/{orderId}/qrcode")
    public ResponseEntity<String> getOrderQrCode(@PathVariable("orderId") String orderId){
        log.info("getOrderQrCode orderId = {}", orderId);

        String qrCodeString = qrCodeMapper.getQrCodeString(orderId);
        log.info("qrCodeString = {}", qrCodeString);

        return new ResponseEntity<>(qrCodeString, HttpStatus.OK);
    }

    @Operation(summary = "토스 결제 승인을 위함")
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
