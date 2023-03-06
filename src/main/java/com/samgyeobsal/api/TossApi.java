package com.samgyeobsal.api;

import com.samgyeobsal.dto.request.TossOrder;
import com.samgyeobsal.mapper.OrderMapper;
import com.samgyeobsal.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
/**
 * @filename TossApi
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
@RequestMapping("/test/order")
@RequiredArgsConstructor
public class TossApi {

 private final OrderService orderService;
 private final OrderMapper orderMapper;

 @PostMapping("/toss")
    public String tossTest(@RequestBody TossOrder tossOrder) {
     System.out.println(tossOrder);
     orderService.saveToss(tossOrder);
     return "성공";
 }

 @GetMapping("/all")
 public Object orderAll() {
  return orderMapper.selectAllOrder();
 }
}
