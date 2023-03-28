package com.samgyeobsal.service;

import com.samgyeobsal.domain.order.*;

import com.samgyeobsal.dto.request.TossOrder;

import com.samgyeobsal.mapper.MemberMapper;
import com.samgyeobsal.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @filename OrderServiceImpl
 * @author 최태승
 * @since 2023.02.24
 * 인터페이스와 구현체를 분리
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.02.24	최태승		최초 생성
 * </pre>
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public String saveOrder(OrderFormDTO orderForm, String email) {

        int row = orderMapper.insertOrder(orderForm, email);
        if(row == 0) throw new RuntimeException("saveOrder Error");

        for (OrderListVO list : orderForm.getOrderList()) {
            for (OrderListDetailVO detail : list.getOrderListDetail()) {
                row = orderMapper.insertOrderItem(orderForm.getOid(), detail.getPoid(), detail.getAmount(), list.getFpprice() * detail.getAmount());
                if(row == 0) throw new RuntimeException("saveOrder Error");
            }
        }

        return orderForm.getOid();
    }

    @Override
    public ProductDetailFormDTO getProductList(String fid) {
        List<ProductDetailVO> productDetail = orderMapper.getProductList(fid);
        OrderFtitleVO orderFtitleVO = orderMapper.getFtitle(fid);

        return new ProductDetailFormDTO(productDetail,orderFtitleVO);
    }

    @Override
    public OrderFormDTO getOrderList(OrderStep1DTO orderStep1DTO,String fid) {
        Map<String, Integer> map = new HashMap<>();
        for (OrderItemVO item : orderStep1DTO.getItem())
            map.put(item.getPoid(), item.getAmount());

        List<OrderListVO> orderList = orderMapper.getOrderList(orderStep1DTO);

        for (OrderListVO orderListVO : orderList) {
            for (OrderListDetailVO orderListDetailVO : orderListVO.getOrderListDetail()) {
                orderListDetailVO.setAmount(map.get(orderListDetailVO.getPoid()));
            }
        }


        OrderFtitleVO orderFtitleVO=orderMapper.getFtitle(fid);

        for (OrderListVO orderListVO : orderList) {
            log.info("sumamount = {}",orderListVO.getSumamount());
            log.info("sumprice = {}", orderListVO.getSumprice());
        }
        return new OrderFormDTO(orderList, orderFtitleVO);
    }

    @Override
    public void updateOrder(String oStatus, String oId) {
        orderMapper.updateOrder(oStatus,oId);
    }

    @Override
    public List<OrderVO> getMyOrderList(String memberId) {
        return orderMapper.findOrderListByMemberId(memberId);
    }

    @Override
    public OrderVO getOrderByOrderId(String orderId) {
        return orderMapper.findOrderByOrderId(orderId);
    }




}
