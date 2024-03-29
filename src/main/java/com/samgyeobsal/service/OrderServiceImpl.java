package com.samgyeobsal.service;

import com.samgyeobsal.domain.order.*;

import com.samgyeobsal.domain.event.UserMileage;
import com.samgyeobsal.mapper.EventMapper;
import com.samgyeobsal.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final EventMapper eventMapper;

    @Override
    @Transactional
    public String saveOrder(OrderFormDTO orderForm, String email) {

        // 쿠폰
        if(orderForm.getCpid() != null && !orderForm.getCpid().equals("")){
            eventMapper.updateUseDate(LocalDateTime.now(), orderForm.getCpid());
        }

        // 마일리지
        if(orderForm.getOused_mileage() != null && !orderForm.getOused_mileage().equals("")){
            eventMapper.updateUsedMileage(orderForm.getOused_mileage(),orderForm.getOid());
            log.info(">>>>>>>>. getOused_mileage()" + orderForm.getOused_mileage());
            UserMileage userMileage = new UserMileage();
            userMileage.setMMileage(orderForm.getOused_mileage());
            userMileage.setMEmail(email);
            userMileage.setOprice(orderForm.getTotalPrice());
            eventMapper.updateUserMileage(userMileage);

        }


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

    /**
     * 해당 펀딩의 상품 상세 리스트와 가게정보를 반환
     * @param fid : 펀딩 아이디
     */
    @Override
    public ProductDetailFormDTO getProductList(String fid) {
        List<ProductDetailVO> productDetail = orderMapper.getProductList(fid);
        OrderFtitleVO orderFtitleVO = orderMapper.getFtitle(fid);

        return new ProductDetailFormDTO(productDetail,orderFtitleVO);
    }

    /**
     * 해당 펀딩의 주문 정보 리스트 반환
     * 상품마다의 수량과 가격계산
     * @param orderStep1DTO : 주문 정보
     * @param fid : 펀딩 아이디
     */
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
            log.info("sumamount = {}",orderListVO.getTotalAmount());
            log.info("sumprice = {}", orderListVO.getSumprice());
        }
        return new OrderFormDTO(orderList, orderFtitleVO);
    }

    @Override
    public void updateOrder(String oStatus, String oId) {
        orderMapper.updateOrder(oStatus,oId);
    }

    /**
     * 해당 이메일의 주문 리스트 반환
     * @param memberId : 회원 이메일
     */
    @Override
    public List<OrderVO> getMyOrderList(String memberId) {
        return orderMapper.findOrderListByMemberId(memberId);
    }

    /**
     * 해당 주문 정보 반환
     * @param orderId : 주문 아이디
     */
    @Override
    public OrderVO getOrderByOrderId(String orderId) {
        return orderMapper.findOrderByOrderId(orderId);
    }

    /**
     * 해당 펀딩의 주문 리스트 반환
     * @param fid : 펀딩 아이디
     */
    @Override
    public List<String> getOrderIdListByFundingId(String fid) {
        return orderMapper.findOrderIdListByFundingId(fid);
    }
}
