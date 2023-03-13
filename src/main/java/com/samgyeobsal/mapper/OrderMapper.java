package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.order.OrderRequest;
import com.samgyeobsal.dto.request.TossOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
 * </pre>
 */
@Mapper
public interface OrderMapper {
    int save(OrderRequest orderRequest);

    @Insert("INSERT INTO ORDERS(OID,  OPHONE, OMEMO, OUSED_MILEAGE, OORIGIN_PRICE, OPRICE, OSTATUS, ODATE, MEMAIL, PMCODE, QRUSED_DATE, CPID)" +
            " VALUES(#{osId}, #{osPhone}, #{osMemo}, #{msMileage}, #{osOriginPrice}, #{osAfterPrice}, #{osState}, " +
            " #{osDate} ,#{osMail} ,#{pmMethod}, #{osDate}, '')")
    public int saveToss(TossOrder tossOrder);

    @Select("select * from ORDERS")
    public List<OrderRequest> selectAllOrder();

    int updateOrder(@Param("oStatus") String oStatus, @Param("oId") String oId);
}
