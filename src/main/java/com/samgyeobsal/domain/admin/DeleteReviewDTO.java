package com.samgyeobsal.domain.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "리뷰 삭제 객체")
public class DeleteReviewDTO {
    @Schema(description = "펀딩 아이디")
    private String fid;
    @Schema(description = "리뷰 작성자 이메일")
    private String memail;
    @Schema(description = "리뷰 분류 (응원 / 후기)")
    private String rtype;
}
