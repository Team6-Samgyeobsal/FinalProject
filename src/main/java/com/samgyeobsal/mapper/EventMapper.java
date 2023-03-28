package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.event.Event;
import com.samgyeobsal.dto.response.UserCouponList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @filename EventMapper
 * @author 최태승
 * @since 2023.03.26
 * Mybatis 매핑 XML에 기재된 SQL을 호출하기 위한 인터페이스
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.26	최태승		최초 생성
 * </pre>
 */
@Mapper
public interface EventMapper {
    Event findByName(String eCouponName);

    List<UserCouponList> findUserCouponList(@Param("mEmail") String mEmail);

    int updateUseDate(@Param("useDt") LocalDateTime useDt, @Param("cpId") String cpId);

}
