package com.samgyeobsal.domain.cart;

import lombok.Data;
import java.util.List;
/**
 * @filename CartVO
 * @author 최태승
 * @since 2023.02.25
 * 장바구니 vo
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.02.25  	최태승        최초 생성
 * </pre>
 */
@Data
public class CartVO {

    private String mId; // 회원아이디
    private String psId; // 상품 재고 PK
    private int pQuantity; // 상품수량
    private String bName; // 브랜드명
    private String pName; // 물품명
    private String pImg1; // 이미지 1개
    private int pPrice; // 판매가격
    private int realPcPrice; // 사이트상 판매 가격
    private String pSize; // 상품 사이즈
    private String pChipImg; // 칩 이미지
    private String pId; // 상품 아이디
    private String favor; // 맛

    private List<String> sizeList;

    private int index; // 인덱스

    public void convert() { // 최종 가격 처리
        this.realPcPrice = pPrice * pQuantity;
    }
}
