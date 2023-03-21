package com.samgyeobsal.service;

import com.samgyeobsal.domain.order.*;

import java.util.List;

import com.samgyeobsal.dto.request.TossOrder;


/**
 * @filename OrderService
 * @author 최태승
 * @since 2023.02.24
 * 주문 서비스 기능 구현
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.02.24	최태승		최초 생성
 * </pre>
 */
public interface OrderService {
    String saveOrder(OrderFormDTO orderForm,String email);


    ProductDetailFormDTO getProductList(String fid);

    OrderFormDTO getOrderList(OrderStep1DTO orderStep1DTO,String fid);


    void updateOrder(String oStatus, String oId);

    List<OrderVO> getMyOrderList(String memberId);

    OrderVO getOrderByOrderId(String orderId);


}
