package com.samgyeobsal.api;

import com.samgyeobsal.domain.order.OrderRequest;
import com.samgyeobsal.service.OrderService;
import lombok.RequiredArgsConstructor;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
public class OrderApi {

    private final OrderService orderService;

    // 결제 진행
    /**
     * 데이터베이스 내 결제 내역을 저장하는 API
     * 성공시 1, 실패시 0을 리턴
     * 카카오페이의 1,2 API를 모두 성공한 후 OrderRequest에 있는 이름에 맞게 데이터를 BODY에 전송
     * 예시 BODY :: 특이사항 (oPhone은 11자리 제한으로 "-"를 빼고 전달해야함)
     * {
     *     "oId" : 1,
     *     "oCount" : 2,
     *     "oConsumer" : "최태승"
     *     "oPhone" : "01012345678"
     *     "oMail" : id@email.com,
     *     "cPid" : "가입축하쿠폰",
     *     "oOriginPrice" : 100,000,
     *     "oAfterPrice" : 90,000,
     *     "mMileage" : 5,000,
     *     "pmMethod" : 1,
     *     "pmCompany" : "KAKAO COMP.",-=[]
     *     "mId" : "isshosng"
     * }
     */

    @PostMapping
    public ResponseEntity<Integer> kakaoPay(@RequestBody OrderRequest request){
        return ResponseEntity.ok().body(orderService.saveOrder(request));
    }

}
