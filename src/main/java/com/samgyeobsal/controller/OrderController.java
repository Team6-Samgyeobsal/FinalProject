package com.samgyeobsal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samgyeobsal.domain.event.Event;
import com.samgyeobsal.domain.order.OrderFormDTO;
import com.samgyeobsal.domain.order.OrderStep1DTO;
import com.samgyeobsal.security.domain.Account;
import com.samgyeobsal.mapper.EventMapper;
import com.samgyeobsal.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Base64;


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
 * 2023.03.26   최태승        쿠폰 적용
 * </pre>
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/web/order")
@Slf4j
public class OrderController {

    @Value("${toss.client.key}")
    private String tossKey;

    @Value("${toss.secret.key}")
    private String secretKey;

    private final ObjectMapper objectMapper;

    private final OrderService orderService;
    private final EventMapper eventMapper;

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
        log.info("orderStep1 orderStep1DTO = {}", orderStep1DTO);

        session.setAttribute("order", orderStep1DTO);
        return "redirect:/web/order/"+fid+"/step2";
    }

    @GetMapping("/{fundingId}/step2")
    public String orderStep2Page(Model model, HttpSession session,@PathVariable String fundingId, @AuthenticationPrincipal Account account){
        OrderStep1DTO orderStep1DTO = (OrderStep1DTO) session.getAttribute("order");
        log.info("orderStep2Page orderStep1DTO = {}", orderStep1DTO);
        model.addAttribute("userCoupon", eventMapper.findUserCouponList(account.getMember().getMemail())); // 쿠폰 적용
        model.addAttribute("order", orderService.getOrderList(orderStep1DTO,fundingId));
        model.addAttribute("tossKey", tossKey);
        return "order/order_step2";
    }

    @GetMapping("/coupon")
    public Event checkCouponName(@RequestParam("couponname") String couponName){
        Event event = eventMapper.findByName(couponName);
        log.info("쿠폰 :" + event);
        return event;
    }

    @GetMapping("/success")
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
            // 쿠폰
            if(orderFormDTO.getCpid() != null && !orderFormDTO.getCpid().equals("")){
                eventMapper.updateUseDate(LocalDateTime.now(), orderFormDTO.getCpid());
            }
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
