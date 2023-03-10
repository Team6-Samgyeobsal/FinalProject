package com.samgyeobsal.service;

import com.samgyeobsal.domain.order.OrderRequest;
import com.samgyeobsal.domain.order.ProductDetailVO;
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
}
