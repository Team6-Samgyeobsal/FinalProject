package com.samgyeobsal.dto.response;

import lombok.Data;
/**
 * @filename UserMileage
 * @author 최태승
 * @since 2023.03.26
 * 유저 마일리지 DTO
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.26	최태승		최초 생성
 * </pre>
 */
@Data
public class UserMileage {

    private String mEmail;
    private Integer mMileage;
    private Integer oprice;
}
