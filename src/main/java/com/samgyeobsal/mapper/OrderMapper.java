package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.order.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @filename OrderMapper
 * @author 최태승
 * @since 2023.02.24
 * Mybatis 매핑 XML에 기재된 SQL을 호출하기 위한 인터페이스
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.02.24	최태승        최초 생성
 * 2023.03.26   최태승        쿠폰 적용
 * 2023.03.30   최태승        마일리지 적용
 * </pre>
 */
@Mapper
public interface OrderMapper {
    int insertOrder(@Param("order") OrderFormDTO order, @Param("email") String email);

    int insertOrderItem(String oid, String poid, int oicount, int oitotalprice);

    List<ProductDetailVO> getProductList(@Param("fid") String fid);

    List<OrderListVO> getOrderList(OrderStep1DTO orderStep1DTO);


    int updateOrder(@Param("oStatus") String oStatus, @Param("oId") String oId);

    List<OrderVO> findOrderListByMemberId(String memberId);

    List<String> findOrderIdListByFundingId(String fid);

    OrderVO findOrderByOrderId(String orderId);


    OrderFtitleVO getFtitle(String fid);
}
