package com.samgyeobsal.dto.response;

import lombok.Data;

/**
 * @filename UserCouponList
 * @author 최태승
 * @since 2023.03.26
 * 유저 쿠폰 DTO
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.26	최태승		최초 생성
 * </pre>
 */
@Data
public class UserCouponList {

    private Integer eId;
    private String eCouponTitle;
    private String eType;
    private Integer eDiscount;
    private String cpId;

}
