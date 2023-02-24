package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.order.OrderRequest;
import org.apache.ibatis.annotations.Mapper;

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
}
