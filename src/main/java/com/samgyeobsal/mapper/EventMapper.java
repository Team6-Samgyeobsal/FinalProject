package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.event.Event;
import com.samgyeobsal.domain.event.UserCouponList;
import com.samgyeobsal.domain.event.UserMileage;
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
 * 2023.03.26	최태승        최초 생성
 * 2023.03.30   최태승        쿠폰, 마일리지 적용
 * </pre>
 */
@Mapper
public interface EventMapper {
    Event findByName(String eCouponName);
    Event findMileage(Integer mMileage);

    List<UserCouponList> findUserCouponList(@Param("mEmail") String mEmail);
    List<UserMileage> findUserMileage(@Param("mEmail") String mEmail);

    int updateUsedMileage(@Param("usedMl") Integer usedMl, @Param("oid") String oid);

    int updateUserMileage(UserMileage userMileage);


    int updateUseDate(@Param("useDt") LocalDateTime useDt, @Param("cpId") String cpId);

}
