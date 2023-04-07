package com.samgyeobsal.domain.maker;


import com.samgyeobsal.domain.funding.ProdOptionVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.lang.annotation.Native;
import java.util.List;

@Data
@Schema(description = "펀딩 상품 수정 객체")
public class UpdateFundingProductDTO {
    @Schema(description = "펀딩아이디")
    private String fid;
    @Schema(description = "펀딩 상품아이디")
    private String fpid;
    @Schema(description = "펀딩 판매 가격")
    private String price;
    @Schema(description = "본래 판매 가격")
    private String originPrice;
    @Schema(description = "펀당 상품 이름")
    private String title;
    @Schema(description = "펀딩 상품 설명")
    private String content;
    @Schema(description = "펀딩 상품 옵션 리스트")
    private List<ProdOptionVO> opts;


}
