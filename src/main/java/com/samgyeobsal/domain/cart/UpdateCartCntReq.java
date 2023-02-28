package com.samgyeobsal.domain.cart;

import lombok.Data;
/**
 * @filename UpdateCartCountReq
 * @author 최태승
 * @since 2023.02.25
 * 장바구니 수량 변경
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.02.25  	최태승        최초 생성
 * </pre>
 */
@Data
public class UpdateCartCntReq {
    private String mId;
    private String psId;
    private int count;

    public void convert(int countNow) {
        this.count = count + countNow;
    }
}
