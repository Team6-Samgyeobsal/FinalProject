package com.samgyeobsal.service;

import com.samgyeobsal.domain.order.*;

import com.samgyeobsal.domain.order.OrderRequest;
import com.samgyeobsal.dto.request.TossOrder;

import com.samgyeobsal.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    public int saveOrder(OrderRequest orderRequest) {

        // String uuid = UUID.randomUUID().toString();

        orderRequest.setOId("isshosng");

        int save = orderMapper.save(orderRequest);

        if (save == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override

    public List<ProductDetailVO> getProductList(String fid) {
        return orderMapper.getProductList(fid);
    }

    @Override
    public OrderFormDTO getOrderList(OrderStep1DTO orderStep1DTO) {
        List<OrderListVO> orderList = orderMapper.getOrderList(orderStep1DTO);
        int totalprice=0;
        for (OrderItemVO orderItemVO : orderStep1DTO.getItem()) {

            for (OrderListVO listVO : orderList) {
                for (OrderListDetailVO detailVO : listVO.getOrderListDetail()) {
                    if (orderItemVO.getPoid().equals(detailVO.getPoid())) {
                        detailVO.setAmount(orderItemVO.getAmount());
                        int price = listVO.getFpprice();
                        int amount = orderItemVO.getAmount();
                        int sumprice = price * amount;
                        int sumamount=amount;
                        totalprice += sumprice;
                        listVO.setSumprice(listVO.getSumprice() + sumprice);
                        listVO.setSumamount(listVO.getSumamount()+amount);
                    }
                }

            }

        }
        System.out.println("orderList++"+orderList);
        System.out.println("totalprice"+totalprice);
        return new OrderFormDTO(orderList,totalprice);
    }

    @Override
    public int saveToss(TossOrder tossOrder) {
        int save = orderMapper.saveToss(tossOrder);

        System.out.println(save);

        if (save == 1) {
            return 1;
        } else {
            return 0;
        }
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
