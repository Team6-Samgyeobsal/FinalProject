package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.order.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.samgyeobsal.dto.request.TossOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

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
 * 2023.02.24	최태승		최초 생성
 * 2023.03.26   최태승        쿠폰 적용
 * </pre>
 */
@Mapper
public interface OrderMapper {
    int insertOrder(@Param("order") OrderFormDTO order, @Param("email") String email);

    int insertOrderItem(String oid, String poid, int oicount, int oitotalprice);

    List<ProductDetailVO> getProductList(@Param("fid") String fid);

    List<OrderListVO> getOrderList(OrderStep1DTO orderStep1DTO);


//    @Insert("INSERT INTO ORDERS(OID,  OPHONE, OMEMO, OUSED_MILEAGE, OORIGIN_PRICE, OPRICE, OSTATUS, ODATE, MEMAIL, PMCODE, QRUSED_DATE, CPID)" +
//            " VALUES(#{osId}, #{osPhone}, #{osMemo}, #{msMileage}, #{osOriginPrice}, #{osAfterPrice}, #{osState}, " +
//            " #{osDate} ,#{osMail} ,#{pmMethod}, #{osDate}, '')")
//    public int saveToss(TossOrder tossOrder);
//
//    @Select("select * from ORDERS")
//    public List<OrderRequest> selectAllOrder();

    int updateOrder(@Param("oStatus") String oStatus, @Param("oId") String oId);

    List<OrderVO> findOrderListByMemberId(String memberId);

    OrderVO findOrderByOrderId(String orderId);


    OrderFtitleVO getFtitle(String fid);
}
